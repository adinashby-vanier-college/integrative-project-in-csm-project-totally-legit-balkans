package edu.vanier.superspace.simulation;

@FunctionalInterface
public interface SimulationTimeCallback {
    void update(double deltaTime);
}
