// UserModel.java
package com.example.projectpam.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectpam.data.DatabaseHelper;

public class UserModel {

    private DatabaseHelper dbHelper;

    public UserModel(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Регистрация пользователя
    public boolean registerUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Проверяем, есть ли уже такой email
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, null,
                DatabaseHelper.COLUMN_EMAIL + "=?",
                new String[]{user.getEmail()}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return false; // Пользователь с таким email уже есть
        }
        cursor.close();

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

    // Логин пользователя
    public boolean login(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, null,
                DatabaseHelper.COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, null,
                DatabaseHelper.COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);
        User user = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String gender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER));
            double height = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_HEIGHT));
            double weight = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_WEIGHT));
            int calorieLimit = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CALORIE_LIMIT));
            int stepLimit = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STEP_LIMIT));

            user = new User(name, email, gender, height, weight, calorieLimit, stepLimit);
        }
        cursor.close();
        db.close();
        return user;
    }

}
