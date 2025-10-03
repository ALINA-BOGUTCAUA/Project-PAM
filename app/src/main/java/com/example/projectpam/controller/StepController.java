package com.example.projectpam.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.projectpam.model.StepModel;

public class StepController {
    private final StepModel model;
    private final Context context;

    public StepController(Context context, StepModel model) {
        this.context = context;
        this.model = model;
    }

    public void onStepDetected() {
        model.addStep();

        if (model.isTargetReached()) {
            Toast.makeText(context, "🎉 Молодцы! Вы выполнили норму!", Toast.LENGTH_LONG).show();
        }
    }

    public StepModel getModel() {
        return model;
    }
}
