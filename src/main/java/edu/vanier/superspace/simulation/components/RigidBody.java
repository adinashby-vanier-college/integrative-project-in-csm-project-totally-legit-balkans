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

    public void attract(Entity entity) {
        Physics.computeAndApplyNewtonGravitation(getEntity(), entity);
        Vector2 distance = getEntity().getTransform().getPosition().subtract(entity.getTransform().getPosition());
        System.out.println(distance.magnitude());
    }

    public void addForce(Vector2 forceVector){
        acceleration.addAssign(forceVector.divide(mass));
    }

    @Override
    public void onUpdate(double deltaTime) {
        velocity.addAssign(acceleration.divide(deltaTime));
        getEntity().getTransform().getPosition().addAssign(velocity);
        acceleration = Vector2.of(0,0);
    }
}
