package com.example.projectpam.view;

import android.app.Activity;
import android.content.Intent; // <-- добавляем импорт
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpam.R;
import com.example.projectpam.controller.StepController;
import com.example.projectpam.model.StepModel;

public class MainActivity extends Activity implements StepController.StepListener {

    private TextView stepsText;
    private TextView caloriesText;
    private TextView distanceText;

    private StepModel model;
    private StepController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Привязка элементов интерфейса
        stepsText = findViewById(R.id.stepsText);
        caloriesText = findViewById(R.id.caloriesText);
        distanceText = findViewById(R.id.distanceText);

        // Привязка navAccount
        TextView navAccount = findViewById(R.id.navAccount);
        navAccount.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class); // сюда вставь свою Activity
            startActivity(intent);
        });

        // Инициализация модели и контроллера
        model = new StepModel();
        controller = new StepController(this, model, this);

        Toast.makeText(this, "Шагомер активен! Начните движение 🚶‍♀️", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.stop();
    }

    @Override
    public void onStepUpdate(int steps, double calories, double distance) {
        stepsText.setText("Шаги: " + steps);
        caloriesText.setText(String.format("Калории: %.1f", calories));
        distanceText.setText(String.format("Расстояние: %.2f км", distance));
    }
}
