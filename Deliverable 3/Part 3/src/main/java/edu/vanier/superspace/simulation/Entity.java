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

/**
 * Entity class for the entities to be rendered in the simulation.
 */
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

    @Override
    public String toString() {
        return "Entity{" +
                "guid=" + guid +
                ", astralBody=" + astralBody +
                ", simulating=" + simulating +
                ", name='" + name + '\'' +
                ", components=" + components +
                '}';
    }

    @Setter @ToSerialize
    protected String name;
    @ToSerialize
    protected final ArrayList<Component> components = new ArrayList<>();

    @Getter @Setter
    protected boolean selected;

    /**
     * Default constructor
     */
    public Entity() {
        this(UUID.randomUUID());
    }

    /**
     * Parameterized constructor
     * @param existingUUID existing UUID
     */
    public Entity (UUID existingUUID) {
        guid = existingUUID;
        transform = new Transform();
        System.out.println(transform);
    }

    /**
     * Registers entity from a loaded file
     */
    public void registerFromLoadedFile() {
        simulation = Simulation.getInstance();
        Simulation.getInstance().registerFromLoadedFile(this);
    }

    /**
     * Registers an entity to the simulation
     */
    public void register() {
        simulation = Simulation.getInstance();
        Simulation.getInstance().createEntity(this);
    }

    /**
     * Destroys/removes an entity from the simulation
     */
    public void destroy() {
        Simulation.getInstance().destroyEntity(this);
    }

    /**
     * Adds a component to the entity.
     * @param component the given component
     */
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

    /**
     * Getter for a given type of component.
     * @param type the type of the component searched
     * @param <T> the class of the component
     */
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
