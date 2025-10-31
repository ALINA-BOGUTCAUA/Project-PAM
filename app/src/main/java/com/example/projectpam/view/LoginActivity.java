package com.example.projectpam.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpam.R;
import com.example.projectpam.controller.UserController;

public class LoginActivity extends Activity {

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userController = new UserController(this);

        EditText etEmail = findViewById(R.id.etEmail);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (userController.login(email)) {
                // Если вход успешен, открываем страницу с данными
                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                intent.putExtra("email", email); // передаем email пользователя
                startActivity(intent);
            } else {
                Toast.makeText(this, "Пользователь не найден", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> {
            // Переход на регистрацию
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
