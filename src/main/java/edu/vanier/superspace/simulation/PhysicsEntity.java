package edu.vanier.superspace.simulation;

import edu.vanier.superspace.simulation.components.AstralBody;
import edu.vanier.superspace.simulation.components.RidigBody;
import edu.vanier.superspace.simulation.components.Transform;
import lombok.Getter;

@Getter
public class PhysicsEntity extends Entity {
    private AstralBody astralBody;
    private RidigBody ridigBody;
}
