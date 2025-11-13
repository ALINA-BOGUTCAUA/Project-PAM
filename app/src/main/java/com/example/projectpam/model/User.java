// User.java
package com.example.projectpam.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String gender;
    private double height;
    private double weight;
    private int calorieLimit;
    private int stepLimit;

    public User(String name, String email, String gender, double height, double weight) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.calorieLimit = calculateCalorieLimit();
        this.stepLimit = calculateStepLimit();
    }

    // --- Расчёт калорий ---
    private int calculateCalorieLimit() {
        // Используем формулу Миффлина — Сан Жеора
        double bmr;
        if (gender.equalsIgnoreCase("мужчина")) {
            bmr = 10 * weight + 6.25 * height - 5 * 30 + 5;  // возраст условно 30
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * 30 - 161;
        }

        // Коэффициент малой активности (сидячий образ жизни)
        double calorieLimit = bmr * 1.2;
        return (int) Math.round(calorieLimit);
    }

    // --- Расчёт шаго-лимита ---
    private int calculateStepLimit() {
        int baseSteps = 7000;

        if (gender.equalsIgnoreCase("мужчина")) {
            baseSteps += (int)((height - 170) * 10);
        } else {
            baseSteps += (int)((height - 160) * 10);
        }

        // Ограничим диапазон
        if (baseSteps < 5000) baseSteps = 5000;
        if (baseSteps > 12000) baseSteps = 12000;

        return baseSteps;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getCalorieLimit() {
        return calorieLimit;
    }

    public int getStepLimit() {
        return stepLimit;
    }
}
