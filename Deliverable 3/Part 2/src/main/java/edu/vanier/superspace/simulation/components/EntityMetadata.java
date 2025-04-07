package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @ToSerialize
public class EntityMetadata extends Component{
    private String description;
    private String type;

    public EntityMetadata() {}

    public EntityMetadata(String description, String type) {
        this();
        this.description = description;
        this.type = type;
    }
}
