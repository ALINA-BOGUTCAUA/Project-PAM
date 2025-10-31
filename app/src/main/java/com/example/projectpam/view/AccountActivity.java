package com.example.projectpam.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectpam.R;
import com.example.projectpam.model.UserModel;
import com.example.projectpam.model.User;

public class AccountActivity extends Activity {

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userModel = new UserModel(this);

        String email = getIntent().getStringExtra("email");
        User user = userModel.getUserByEmail(email); // создаём метод в UserModel для получения данных

        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvGender = findViewById(R.id.tvGender);

        if (user != null) {
            tvName.setText("Имя: " + user.getName());
            tvEmail.setText("Email: " + user.getEmail());
            tvGender.setText("Пол: " + user.getGender());
        }
    }
}
