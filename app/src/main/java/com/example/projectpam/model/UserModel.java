package com.example.projectpam.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectpam.data.DatabaseHelper;

public class UserModel {

    private final DatabaseHelper dbHelper;

    public UserModel(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // --- Регистрация пользователя ---
    public boolean registerUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Проверка на существующий email
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                null,
                DatabaseHelper.COLUMN_EMAIL + "=?",
                new String[]{user.getEmail()},
                null, null, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return false; // Пользователь уже существует
        }

        cursor.close();

        // Добавляем нового пользователя
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, user.getName());
        values.put(DatabaseHelper.COLUMN_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_GENDER, user.getGender());
        values.put(DatabaseHelper.COLUMN_HEIGHT, user.getHeight());
        values.put(DatabaseHelper.COLUMN_WEIGHT, user.getWeight());
        values.put(DatabaseHelper.COLUMN_CALORIE_LIMIT, user.getCalorieLimit());
        values.put(DatabaseHelper.COLUMN_STEP_LIMIT, user.getStepLimit());

        long id = db.insert(DatabaseHelper.TABLE_USER, null, values);
        db.close();

        return id != -1;
    }

    // --- Авторизация пользователя ---
    public boolean login(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                null,
                DatabaseHelper.COLUMN_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }

    // --- Получение пользователя по email ---
    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                null,
                DatabaseHelper.COLUMN_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        User user = null;

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENDER));
            double height = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_HEIGHT));
            double weight = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_WEIGHT));

            // Здесь лимиты пересчитаются автоматически внутри конструктора
            user = new User(name, email, gender, height, weight);
        }

        cursor.close();
        db.close();
        return user;
    }
}
