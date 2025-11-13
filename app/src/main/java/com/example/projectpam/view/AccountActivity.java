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
        User user = userModel.getUserByEmail(email);

        // Находим элементы интерфейса
        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvGender = findViewById(R.id.tvGender);
        TextView tvHeight = findViewById(R.id.tvHeight);
        TextView tvWeight = findViewById(R.id.tvWeight);
        TextView tvCalorieLimit = findViewById(R.id.tvCalorieLimit);
        TextView tvStepLimit = findViewById(R.id.tvStepLimit);

        if (user != null) {
            tvName.setText("Имя: " + user.getName());
            tvEmail.setText("Email: " + user.getEmail());
            tvGender.setText("Пол: " + user.getGender());
            tvHeight.setText("Рост: " + user.getHeight() + " см");
            tvWeight.setText("Вес: " + user.getWeight() + " кг");
            tvCalorieLimit.setText("Лимит калорий: " + user.getCalorieLimit() + " ккал/день");
            tvStepLimit.setText("Лимит шагов: " + user.getStepLimit() + " шагов");
        }
    }
}
