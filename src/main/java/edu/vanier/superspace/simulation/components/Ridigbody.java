package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Tickable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RidigBody extends Component implements Tickable  {
    
    
    private Vector2 velocity = Vector2.of(0, 0);
    private double mass = 1.0;
    private Vector2 acceleration = Vector2.of(0, 0);
    
    public void addForce(Vector2 forceVector){
        
        acceleration.addAssign(forceVector.divide(mass));
        
    }

    @Override
    public void onUpdate(double deltaTime) {
        
        velocity.addAssign(acceleration.divide(deltaTime));
        acceleration = Vector2.of(0,0);
        
    }
    
}
