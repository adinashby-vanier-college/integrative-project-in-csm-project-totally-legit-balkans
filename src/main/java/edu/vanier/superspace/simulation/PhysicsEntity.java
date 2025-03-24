package edu.vanier.superspace.simulation;

import edu.vanier.superspace.simulation.components.RigidBody;
import lombok.Getter;

@Getter
public class PhysicsEntity extends Entity {
    private RigidBody rigidBody;
}
