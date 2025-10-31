package com.example.projectpam.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 1;

    // Таблица пользователя
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_CALORIE_LIMIT = "calorie_limit";
    public static final String COLUMN_STEP_LIMIT = "step_limit";

    // Таблица дневной статистики
    public static final String TABLE_STATS = "stats";
    public static final String COLUMN_DATE = "date"; // YYYY-MM-DD
    public static final String COLUMN_STEPS = "steps";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_DISTANCE = "distance";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицу пользователей
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_HEIGHT + " REAL,"
                + COLUMN_WEIGHT + " REAL,"
                + COLUMN_CALORIE_LIMIT + " INTEGER,"
                + COLUMN_STEP_LIMIT + " INTEGER"
                + ")";
        db.execSQL(CREATE_USER_TABLE);

        // Создаем таблицу статистики
        String CREATE_STATS_TABLE = "CREATE TABLE " + TABLE_STATS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_STEPS + " INTEGER,"
                + COLUMN_CALORIES + " REAL,"
                + COLUMN_DISTANCE + " REAL"
                + ")";
        db.execSQL(CREATE_STATS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        onCreate(db);
    }
}
