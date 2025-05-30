package edu.vanier.superspace.simulation;

import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;

import lombok.Getter;

/**
 * Used for the physics of an entity
 */
@Getter
public class PhysicsEntity extends Entity {
    private RigidBody rigidBody;
}
