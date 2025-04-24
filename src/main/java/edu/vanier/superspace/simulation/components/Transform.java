package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.mathematics.Vector2;
import lombok.Getter;
import lombok.Setter;

/**
 * Transform component of an entity displaying its position, scale and rotation
 */
@Getter
@Setter
@ToSerialize
public class Transform extends Component  {
    private Vector2 position;
    private Vector2 scale;
    private double rotation;

    /**
     * Default constructor
     */
    public Transform() {
        this.position = Vector2.zero();
        this.scale = Vector2.one();
        this.rotation = 0;
    }
}
