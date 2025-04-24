package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.mathematics.Constants;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Tickable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * A component that also extends the Tickable interface and is used to calculate the force upon an astral body
 */
@Getter @Setter
public class RigidBody extends Component implements Tickable  {
    private Vector2 velocity = Vector2.of(0, 0);
    private double mass = 1.0;
    private Vector2 acceleration = Vector2.of(0, 0);
    private boolean isAttractor = false;

    /**
     * Default constructor
     */
    public RigidBody() {}

    /**
     * Parameterized constructor
     * @param mass the mass of the entity
     */
    public RigidBody(double mass) {
        this();
        this.mass = mass;
    }

    /**
     * Changes the acceleration based on the given force
     * @param forceVector the given force
     */
    public void addForce(Vector2 forceVector){
        acceleration.addAssign(forceVector.divide(mass));
    }

    /**
     * Attraction physics with another entity that are based on the law of universal gravitation
     * @param entity the second entity
     */
    public void attract(Entity entity, double deltaTime) {
        Vector2 distance = getEntity().getTransform().getPosition().subtract(entity.getTransform().getPosition());
        double distanceMag = distance.magnitude();

        if (distanceMag < 2500) {
            distanceMag = Math.max(5, Math.min(distanceMag, 25));

            Vector2 force = distance.normalized().multiply((Constants.SIMULATION_GRAVITATIONAL_CONSTANT *
                    getEntity().getRigidBody().getMass() *
                    entity.getRigidBody().getMass()) / Math.pow(distanceMag, 2));

            entity.getRigidBody().addForce(force.multiply(deltaTime));
        }
    }

    /**
     * Tickable interface on update,
     * acceleration is being added to velocity, and it is then made the zero vector,
     * velocity is added to the position.
     * @param deltaTime delta time
     */
    @Override
    public void onUpdate(double deltaTime) {
        for (Entity entity2 : entity.getSimulation().getEntities()) {
            if (entity2 == entity) continue;

            if (entity2.getRigidBody() != null) {
                if (entity2.getRigidBody().isAttractor) continue;

                entity2.getRigidBody().attract(this.entity, deltaTime);
            }
        }
        
        velocity.addAssign(acceleration);
        getEntity().getTransform().getPosition().addAssign(velocity);
        acceleration = Vector2.of(0,0);
    }
}
