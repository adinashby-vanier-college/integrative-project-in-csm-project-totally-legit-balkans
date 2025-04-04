package edu.vanier.superspace.simulation.components;

import com.google.gson.FieldAttributes;
import edu.vanier.superspace.mathematics.Constants;
import edu.vanier.superspace.mathematics.Physics;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Tickable;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

@Getter @Setter
public class RigidBody extends Component implements Tickable  {
    private Vector2 velocity = Vector2.of(0, 0);
    private double mass = 1.0;
    private Vector2 acceleration = Vector2.of(0, 0);

    public RigidBody() {}

    public RigidBody(double mass) {
        this();
        this.mass = mass;
    }

    public void addForce(Vector2 forceVector){
        acceleration.addAssign(forceVector.divide(mass));
    }

    public void attract(Entity entity) {
//        Vector2 force = getEntity().getTransform().getPosition().subtractAssign(entity.getTransform().getPosition());
//        double distance = force.magnitude();
//        distance = Math.max(5, Math.min(distance, 25));
//
//        double strength = (Constants.SIMULATION_GRAVITATIONAL_CONSTANT * this.getMass() * entity.getRigidBody().getMass()) / (distance * distance);

        Vector2 distance = getEntity().getTransform().getPosition().subtract(entity.getTransform().getPosition());
        double distanceMag = distance.magnitude();
        distanceMag = Math.max(5, Math.min(distanceMag, 25));
        System.out.println(distanceMag);
        Vector2 force = distance.normalized().multiply((Constants.SIMULATION_GRAVITATIONAL_CONSTANT *
                getEntity().getRigidBody().getMass() *
                entity.getRigidBody().getMass() )/ Math.pow(distanceMag, 2));

        entity.getRigidBody().addForce(force);
    }

    @Override
    public void onUpdate(double deltaTime) {
        velocity.addAssign(acceleration);
        getEntity().getTransform().getPosition().addAssign(velocity);
        System.out.println(velocity);
        acceleration = Vector2.of(0,0);
    }
}
