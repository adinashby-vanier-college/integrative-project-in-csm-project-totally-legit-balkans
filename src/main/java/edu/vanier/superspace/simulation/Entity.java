package edu.vanier.superspace.simulation;

import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.simulation.components.Renderer;
import edu.vanier.superspace.simulation.components.Transform;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Getter
public class Entity {
    private UUID guid;
    protected Transform transform;
    protected Simulation simulation;
    protected Renderer renderer;
    @Setter
    protected boolean simulating;
    @Setter
    protected String name;
    protected final ArrayList<Component> components = new ArrayList<>();

    public Entity() {
        this(UUID.randomUUID());
    }

    public Entity (UUID existingUUID) {
        guid = existingUUID;
        transform = new Transform();
        System.out.println(transform);
    }

    public void register() {
        simulation = Simulation.getInstance();
        Simulation.getInstance().createEntity(this);
    }

    public void destroy() {
        Simulation.getInstance().destroyEntity(this);
    }

    public void addComponent(Component component) {
        if (component instanceof Transform tf) {
            transform = tf;
        }

        if (component instanceof Renderer r) {
            renderer = r;
        }

        components.add(component);
        component.setEntity(this);
    }

    public <T extends Component> void getComponentOfType(Class<T> type) {

    }
}
