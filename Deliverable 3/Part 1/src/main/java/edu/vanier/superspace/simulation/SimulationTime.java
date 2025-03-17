package edu.vanier.superspace.simulation;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class SimulationTime extends AnimationTimer {
    private final ArrayList<SimulationTimeCallback> callbacks = new ArrayList<>();

    private long lastUpdateTime = 0;

    @Override
    public void handle(long now) {
        if (lastUpdateTime == 0) {
            lastUpdateTime = now;
            return;
        }

        long elapsedNanoseconds = lastUpdateTime - now;
        lastUpdateTime = now;

        double deltaTime = (double)elapsedNanoseconds / 1e9;
        callbacks.forEach((callback) -> callback.update(deltaTime));
    }

    public void registerCallback(SimulationTimeCallback callback) {
        callbacks.add(callback);
    }
}
