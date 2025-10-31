package com.example.projectpam.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.projectpam.model.StepModel;

/**
 * Контроллер — обрабатывает данные акселерометра, определяет шаги
 * и обновляет модель.
 */
public class StepController implements SensorEventListener {

    private static final float STEP_THRESHOLD = 11f; // порог для шага
    private static final long STEP_DELAY_NS = 250_000_000L; // 0.25 сек между шагами

    private final StepModel model;
    private final StepListener listener;
    private final SensorManager sensorManager;

    private long lastStepTimeNs = 0;
    private boolean isWalking = false;

    public interface StepListener {
        void onStepUpdate(int steps, double calories, double distance);
    }

    public StepController(Context context, StepModel model, StepListener listener) {
        this.model = model;
        this.listener = listener;
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void start() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) return;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        double magnitude = Math.sqrt(x * x + y * y + z * z);
        long now = System.nanoTime();

        // Если амплитуда превышает порог → человек двигается
        if (magnitude > STEP_THRESHOLD) {
            if (!isWalking && (now - lastStepTimeNs > STEP_DELAY_NS)) {
                model.addStep();
                listener.onStepUpdate(model.getSteps(), model.getCalories(), model.getDistance());
                lastStepTimeNs = now;
                isWalking = true;
            }
        } else {
            // если движение прекратилось, сбрасываем флаг ходьбы
            isWalking = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
