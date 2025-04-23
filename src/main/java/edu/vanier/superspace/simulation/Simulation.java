package edu.vanier.superspace.simulation;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.Camera;
import edu.vanier.superspace.simulation.components.DebugCircleRenderer;
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

        Entity cursorTracker = new Entity();
        cursorTracker.addComponent(new DebugCircleRenderer());
        cursorTracker.register();

        userCatalog = new UserCatalog();
    }

    public void registerCamera(Camera camera) {
        clock.getComponentsToTick().add(camera);
    }

    public void setInitialCanvasHeights() {
        for (Canvas canvas : canvases) {
            canvas.setWidth(canvasStack.getWidth());
            canvas.setHeight(canvasStack.getHeight());
        }
    }

    public void registerFromLoadedFile(Entity entity) {
        clock.registerEntityToUpdate(entity);
    }

    public void createEntity(Entity entity) {
        entities.add(entity);
        clock.registerEntityToUpdate(entity);
    }

    public void destroyEntity(Entity entity) {
        entities.removeIf((e) -> e.getGuid() == entity.getGuid());
        clock.removeComponentsLinkedToEntity(entity);
    }

    public void run() {
        clock.setRunning(true);
    }

    public void stop() {
        clock.setRunning(false);
    }

    public void Step() {
        clock.step();
    }

    public void onSceneClicked(MouseEvent event) {
    }

    public void onSceneClicked(Vector2 position) {

    }

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
