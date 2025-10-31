package com.example.projectpam.model;

/**
 * Модель данных шагомера — хранит шаги, калории и расстояние.
 * Не содержит кода Android (чистая логика).
 */
public class StepModel {

    private int steps = 0;
    private double calories = 0.0;
    private double distance = 0.0;

    private static final double CALORIES_PER_STEP = 0.04; // ~0.04 ккал за шаг
    private static final double STEP_LENGTH_METERS = 0.7; // средняя длина шага 70 см

    public void addStep() {
        steps++;
        updateCalories();
        updateDistance();
    }

    private void updateCalories() {
        calories = steps * CALORIES_PER_STEP;
    }

    private void updateDistance() {
        distance = (steps * STEP_LENGTH_METERS) / 1000.0; // км
    }

    public int getSteps() {
        return steps;
    }

    public double getCalories() {
        return calories;
    }

    public double getDistance() {
        return distance;
    }

    public void reset() {
        steps = 0;
        calories = 0.0;
        distance = 0.0;
    }
}
