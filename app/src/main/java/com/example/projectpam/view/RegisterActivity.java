// RegisterActivity.java
package com.example.projectpam.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectpam.R;
import com.example.projectpam.controller.UserController;

public class RegisterActivity extends AppCompatActivity {

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userController = new UserController(this);

        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etGender = findViewById(R.id.etGender);
        EditText etHeight = findViewById(R.id.etHeight);
        EditText etWeight = findViewById(R.id.etWeight);
        EditText etCalorieLimit = findViewById(R.id.etCalorieLimit);
        EditText etStepLimit = findViewById(R.id.etStepLimit);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String gender = etGender.getText().toString();
            double height = Double.parseDouble(etHeight.getText().toString());
            double weight = Double.parseDouble(etWeight.getText().toString());
            int calorieLimit = Integer.parseInt(etCalorieLimit.getText().toString());
            int stepLimit = Integer.parseInt(etStepLimit.getText().toString());

            userController.register(name, email, gender, height, weight, calorieLimit, stepLimit);
        });
    }
}
