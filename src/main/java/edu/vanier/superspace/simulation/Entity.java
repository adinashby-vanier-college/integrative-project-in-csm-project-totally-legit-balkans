package edu.vanier.superspace.simulation;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.simulation.components.Renderer;
import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.AstralBody;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Getter
public class Entity {
    @ToSerialize
    private UUID guid;
    protected Transform transform;
    protected Simulation simulation;
    protected RigidBody rigidBody;
    protected Renderer renderer;
    @Setter @ToSerialize
    protected AstralBody astralBody;
    @Setter @ToSerialize
    protected boolean simulating;
    @Setter @ToSerialize
    protected String name;
    @ToSerialize
    protected final ArrayList<Component> components = new ArrayList<>();

    public Entity() {
        this(UUID.randomUUID());
    }

    public Entity (UUID existingUUID) {
        guid = existingUUID;
        transform = new Transform();
        System.out.println(transform);
    }

    public void registerFromLoadedFile() {
        simulation = Simulation.getInstance();
        Simulation.getInstance().registerFromLoadedFile(this);
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

        if (component instanceof RigidBody rb) {
            rigidBody = rb;
        }

        components.add(component);
        component.setEntity(this);
    }

    public <T extends Component> T getComponentOfType(Class<T> type) {
        for (Component component : components) {
            if (component.getClass().equals(type)) {
                return (T)component;
            }
        }

        return null;
    }

    public <T extends Component> ArrayList<T> getComponentsOfType(Class<T> type) {
        ArrayList<T> retVal = new ArrayList<>();

        for (Component component : components) {
            if (component.getClass().equals(type)) {
                retVal.add((T)component);
            }
        }

        if (retVal.isEmpty()) {
            return null;
        }

        return retVal;
    }
}
