package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.mathematics.Vector2;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transform extends Component  {
    private Vector2 position;
    private Vector2 scale;
    private double rotation;

    public Transform() {
        this.position = Vector2.zero();
        this.scale = Vector2.one();
        this.rotation = 0;
    }
}
