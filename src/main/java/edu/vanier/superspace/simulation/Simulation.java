package edu.vanier.superspace.simulation;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.Camera;
import edu.vanier.superspace.utils.SaveManager;
import edu.vanier.superspace.utils.AstralBody;
import edu.vanier.superspace.utils.UserCatalog;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Main class for the simulation and that handles almost everything related with the simulation
 */
public class Simulation {
    @Getter
    private static Simulation instance = null;
    @Getter
    private final UserCatalog userCatalog;
    @Getter @ToSerialize
    private final ArrayList<Entity> entities = new ArrayList<>();
    @ToSerialize
    private String name;

    @Getter
    private StackPane canvasStack;

    @Getter
    private final Canvas[] canvases = new Canvas[RenderLayers.values().length];
    @Getter
    private final EnumSet<RenderLayers> activeRenderLayers = EnumSet.of(RenderLayers.DEBUG,
            RenderLayers.SPACE_SIMULATION);

    @Getter
    private final SimulationTimer clock = new SimulationTimer();

    /**
     * Parameterized constructor
     * @param canvasStack the canvas stack
     */
    public Simulation(StackPane canvasStack) {
        this.canvasStack = canvasStack;

        if (Simulation.instance != null) {
            Simulation.instance.StopAndClose(true);
        }

        for (RenderLayers layer : RenderLayers.values()) {
            Canvas newCanvas = new Canvas();
            canvases[layer.ordinal()] = newCanvas;
            canvasStack.getChildren().add(newCanvas);
        }

        Simulation.instance = this;

        new Camera();

        clock.setLinkedSimulation(this);
        clock.start();

        userCatalog = new UserCatalog();
    }

    /**
     * Adds a camera to the simulation and to the components to tick
     * @param camera the instance of the camera
     */
    public void registerCamera(Camera camera) {
        clock.getComponentsToTick().add(camera);
    }

    /**
     * Sets the initial heights of the canvas
     */
    public void setInitialCanvasHeights() {
        for (Canvas canvas : canvases) {
            canvas.setWidth(canvasStack.getWidth());
            canvas.setHeight(canvasStack.getHeight());
        }
    }

    /**
     * Registers an entity from a loaded file
     * @param entity an entity to be registered
     */
    public void registerFromLoadedFile(Entity entity) {
        clock.registerEntityToUpdate(entity);
    }

    /**
     * Adds an entity to the simulation an entities to be rendered
     * @param entity the entity to be added
     */
    public void createEntity(Entity entity) {
        entities.add(entity);
        clock.registerEntityToUpdate(entity);
    }

    /**
     * Removes an entity from the simulation
     * @param entity the entity to be removed
     */
    public void destroyEntity(Entity entity) {
        entities.removeIf((e) -> e.getGuid() == entity.getGuid());
        clock.removeComponentsLinkedToEntity(entity);
    }

    /**
     * Runs the simulation
     */
    public void Run() {
        clock.setRunning(true);
    }

    /**
     * Stops the simulation
     */
    public void Stop() {
        clock.setRunning(false);
    }

    /**
     * Makes the simulation go faster
     */
    public void Step() {
        clock.step();
    }

    /**
     * Gets when the scene is clicked
     * @param event mouse click event
     */
    public void onSceneClicked(MouseEvent event) {
    }

    /**
     * Gets when the scene is clicked but as a vector position
     * @param position position in vector notation
     */
    public void onSceneClicked(Vector2 position) {

    }

    /**
     * Stops and closes the simulation
     * @param saveBeforeQuitting if true it saves the simulation before closing the application
     */
    public void StopAndClose(boolean saveBeforeQuitting) {
        clock.setRunning(false);
        if (saveBeforeQuitting) {
            SaveManager.save();
        }
        instance = null;
        Camera.setInstance(null);
        Platform.exit();
    }
}
