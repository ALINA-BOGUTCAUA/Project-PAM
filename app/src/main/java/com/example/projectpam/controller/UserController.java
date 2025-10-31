// UserController.java
package com.example.projectpam.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.projectpam.model.User;
import com.example.projectpam.model.UserModel;

public class UserController {

    private UserModel userModel;
    private Context context;

    public UserController(Context context) {
        this.context = context;
        userModel = new UserModel(context);
    }

    // Регистрация
    public void register(String name, String email, String gender, double height, double weight, int calorieLimit, int stepLimit) {
        User user = new User(name, email, gender, height, weight, calorieLimit, stepLimit);
        boolean success = userModel.registerUser(user);
        if (success) {
            Toast.makeText(context, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Пользователь с таким email уже существует", Toast.LENGTH_SHORT).show();
        }
    }

    // Вход
    public boolean login(String email) {
        boolean success = userModel.login(email);
        if (success) {
            Toast.makeText(context, "Вход выполнен!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_SHORT).show();
        }
        return success; // <-- возвращаем результат
    }
}
