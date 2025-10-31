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

    public User(String name, String email, String gender, double height, double weight, int calorieLimit, int stepLimit) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.calorieLimit = calorieLimit;
        this.stepLimit = stepLimit;
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
