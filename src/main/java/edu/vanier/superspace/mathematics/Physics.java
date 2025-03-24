package edu.vanier.superspace.mathematics;

import edu.vanier.superspace.simulation.PhysicsEntity;

public class Physics {
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
}
