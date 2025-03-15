package edu.vanier.superspace.simulation;

import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.simulation.components.Transform;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class Entity {
    private long guid;
    private Transform transform;
    private Simulation simulation;
    @Setter
    private boolean simulating;
    @Setter
    private String name;
    private final ArrayList<Component> components = new ArrayList<>();

    public Entity() {

    }

    public void addComponent(Component component) {

    }

    public <T extends Component> void getComponentOfType(Class<T> type) {

    }
}
