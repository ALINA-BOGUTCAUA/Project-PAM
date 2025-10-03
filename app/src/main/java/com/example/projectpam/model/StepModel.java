package com.example.projectpam.model;

public class StepModel {
    private int steps;
    private double calories;
    private double distance;

    private final int targetSteps = 6000;
    private final double targetCalories = 500;

    public void addStep() {
        steps++;
        calculateCalories();
        calculateDistance();
    }

    private void calculateCalories() {
        // ~0.04 ккал за шаг
        this.calories = steps * 0.04;
    }

    private void calculateDistance() {
        // средняя длина шага 0.7 м → переводим в км
        this.distance = steps * 0.7 / 1000.0;
    }

    public int getSteps() { return steps; }
    public double getCalories() { return calories; }
    public double getDistance() { return distance; }

    public boolean isTargetReached() {
        return steps >= targetSteps && calories >= targetCalories;
    }
}
