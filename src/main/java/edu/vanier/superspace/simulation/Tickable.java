package edu.vanier.superspace.simulation;

/**
 * Tickable interface used for components that need to be updated each tick
 */
public interface Tickable {
    void onUpdate(double deltaTime);
}
