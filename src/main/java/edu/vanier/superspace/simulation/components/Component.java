package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.simulation.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class for components, also a parent class for all possible components.
 */
@Getter
public abstract class Component {
    @Setter
    protected Entity entity;
    private boolean initialized = false;

    /**
     * Called upon initialization of a component
     */
    public void onInitialize() {
        initialized = true;
    }
}
