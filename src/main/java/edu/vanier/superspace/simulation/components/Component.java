package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.simulation.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Component {
    @Setter
    protected Entity entity;
    private boolean initialized = false;

    public void onInitialize() {
        initialized = true;
    }
}
