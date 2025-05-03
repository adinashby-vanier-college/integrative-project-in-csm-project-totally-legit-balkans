package edu.vanier.superspace.mathematics;

import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.PhysicsEntity;

/**
 * Class that handles the physics of the simulation
 */
public class Physics {
    /**
     * Application of the law of universal gravitation with the actual gravitational constant
     * @param entity1 the first entity
     * @param entity2 the second entity
     */
    public static void computeAndApplyNewtonGravitation(PhysicsEntity entity1, PhysicsEntity entity2) {
        
        Vector2 totalForce1_2 = Vector2.of(0, 0);
        Vector2 totalForce2_1 = Vector2.of(0, 0);
        
        Vector2 distance = entity1.getTransform().getPosition().subtract(entity2.getTransform().getPosition());
        
        totalForce1_2 = distance.normalized().multiply((Constants.GRAVITATIONAL_CONSTANT * 
                entity1.getRigidBody().getMass() *
                entity2.getRigidBody().getMass() )/ Math.pow(distance.magnitude(), 2));
        
        totalForce2_1 = totalForce1_2.negate();
        
        entity1.getRigidBody().addForce(totalForce1_2);
        entity2.getRigidBody().addForce(totalForce2_1);
    }

    /**
     * Application of the law of universal gravitation with the simulation gravitational constant
     * @param entity1 the first entity
     * @param entity2 the second entity
     */
    public static void computeAndApplyNewtonGravitation(Entity entity1, Entity entity2, double deltaTime) {
        Vector2 distance = entity2.getTransform().getPosition().subtract(entity1.getTransform().getPosition());
        double distanceMag = distance.magnitude();

        if (distanceMag < 10000) {
           // distanceMag = Math.max(5, Math.min(distanceMag, 25));

            Vector2 force = distance.normalized().multiply((Constants.SIMULATION_GRAVITATIONAL_CONSTANT *
                    entity2.getRigidBody().getMass() *
                    entity1.getRigidBody().getMass()) / Math.pow(distanceMag, 2));

            entity1.getRigidBody().addForce(force);
        }
    }
}
