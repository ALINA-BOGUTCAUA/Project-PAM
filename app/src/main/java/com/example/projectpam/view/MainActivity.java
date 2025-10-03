package com.example.projectpam.view;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpam.R;
import com.example.projectpam.controller.StepController;
import com.example.projectpam.model.StepModel;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView stepsText, caloriesText, distanceText;

    private StepController controller;

    private boolean activityRunning;
    private static final float STEP_THRESHOLD = 10f;
    private static final int STEP_DELAY_NS = 250_000_000; // 0.25 сек

    private long lastStepTimeNs = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepsText = findViewById(R.id.stepsText);
        caloriesText = findViewById(R.id.caloriesText);
        distanceText = findViewById(R.id.distanceText);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        StepModel model = new StepModel();
        controller = new StepController(this, model);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;

        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelSensor != null) {
            sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Акселерометр недоступен!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning && event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double magnitude = Math.sqrt(x * x + y * y + z * z);
            long now = System.nanoTime();

            if (magnitude > STEP_THRESHOLD) {
                if (now - lastStepTimeNs > STEP_DELAY_NS) {
                    controller.onStepDetected();
                    updateUI();
                    lastStepTimeNs = now;
                }
            }
        }
    }

    private void updateUI() {
        StepModel model = controller.getModel();
        stepsText.setText("Шаги: " + model.getSteps());
        caloriesText.setText("Калории: " + String.format("%.1f", model.getCalories()));
        distanceText.setText("Расстояние: " + String.format("%.2f км", model.getDistance()));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
