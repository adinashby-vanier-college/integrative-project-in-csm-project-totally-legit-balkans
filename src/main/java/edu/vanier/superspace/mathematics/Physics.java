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

    /**
     * Application of the law of universal gravitation with the simulation gravitational constant
     * @param entity1 the first entity
     * @param entity2 the second entity
     */
    public static void computeAndApplyNewtonGravitation(Entity entity1, Entity entity2, double deltaTime) {
        Vector2 distance = entity2.getTransform().getPosition().subtract(entity1.getTransform().getPosition());
        double distanceMag = distance.magnitude();

        if (distanceMag < 2500) {
           // distanceMag = Math.max(5, Math.min(distanceMag, 25));

           
           
            Vector2 force = distance.normalized().multiply(computeForce(distanceMag, entity1.getAstralBody().getMass(), entity2.getAstralBody().getMass()));

            entity1.getRigidBody().addForce(force);
        }
    }
    
    public static double computeForce(double distanceMag, double firstMass,
            double secondMass){
        
        return (Constants.SIMULATION_GRAVITATIONAL_CONSTANT * firstMass * secondMass) / Math.pow(distanceMag, 2);
        
    }
}
