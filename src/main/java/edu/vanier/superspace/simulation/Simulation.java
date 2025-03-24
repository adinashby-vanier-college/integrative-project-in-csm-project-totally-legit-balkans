package edu.vanier.superspace.simulation;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.Camera;
import edu.vanier.superspace.simulation.components.DebugCircleRenderer;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.SaveManager;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.EnumSet;

public class Simulation {
    @Getter
    private static Simulation instance = null;

    private final ArrayList<Entity> entities = new ArrayList<>();
    private String name;

    @Getter
    private StackPane canvasStack;

    @Getter
    private final Canvas[] canvases = new Canvas[RenderLayers.values().length];
    @Getter
    private final EnumSet<RenderLayers> activeRenderLayers = EnumSet.of(RenderLayers.DEBUG);

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

        Camera camera = new Camera();
        camera.register();
        Entity entity = new Entity();
        entity.addComponent(new DebugCircleRenderer());
        entity.addComponent(new Transform());
        entity.setName("Entity 1");

        entity.register();

        clock.setLinkedSimulation(this);
        clock.start();
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

    public void createEntity(Entity entity) {
        entities.add(entity);
        clock.registerEntityToUpdate(entity);
    }

    public void destroyEntity(Entity entity) {
        entities.removeIf((e) -> e.getGuid() == entity.getGuid());
        clock.removeComponentsLinkedToEntity(entity);
    }

    public void Run() {
        clock.setRunning(true);
    }

    public void Stop() {
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
