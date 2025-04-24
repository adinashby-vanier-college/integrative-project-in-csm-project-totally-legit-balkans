package edu.vanier.superspace.simulation;

import edu.vanier.superspace.controllers.ControlBarFXMLController;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Physics;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

/**
 * Handles all the updates and components to tick/render
 */
public class SimulationTimer extends AnimationTimer {
    @Getter
    private final ArrayList<Tickable> componentsToTick = new ArrayList<>();
    private final ArrayList<Renderer> componentsToRender = new ArrayList<>();

    @Setter
    private boolean running = false;
    private boolean stepOnce = false;

    @Setter
    private Simulation linkedSimulation;
    private long lastUpdateTime = 0;
    @Getter @Setter
    private long startTime = 0;
    @Getter @Setter
    private long elapsedTime = 0;

    @Getter @Setter
    private double timeMultiplier = 1;

    /**
     * Steps the simulation
     */
    public void step() {
        stepOnce = true;
    }

    /**
     * AnimationTimer handle method
     * @param now
     *            The timestamp of the current frame given in nanoseconds. This
     *            value will be the same for all {@code AnimationTimers} called
     *            during one frame.
     */
    @Override
    public void handle(long now) {
        if (lastUpdateTime == 0) {
            lastUpdateTime = now;
            linkedSimulation.setInitialCanvasHeights();
            return;
        }

        long elapsedNanoseconds = now - lastUpdateTime;
//        if (elapsedNanoseconds < 1e9) {
//            return;
//        }

        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

        lastUpdateTime = now;

        double deltaTime = (double)elapsedNanoseconds / 1e9;

        if (stepOnce) {
            tick(0.1 * timeMultiplier);
            stepOnce = false;
        }

        if (running) {
            tick(deltaTime * timeMultiplier);
        }

        Camera.getInstance().onUpdate(deltaTime);
        Input.update();
        tickSelectionCheck();
        clearScreen();
        draw();
    }

    /**
     * Checks for components that are to tick and to render and adds them to the ArrayList
     * @param entity the said entity
     */
    public void registerEntityToUpdate(Entity entity) {
        for (Component component : entity.getComponents()) {
            if (component instanceof Tickable tickable) {
                componentsToTick.add(tickable);
            }

            if (component instanceof Renderer renderer) {
                componentsToRender.add(renderer);
            }
        }
    }

    /**
     * Removes any components that are linked to an entity from the simulation
     * @param entity the entity
     */
    public void removeComponentsLinkedToEntity(Entity entity) {
        componentsToTick.removeIf((c) -> ((Component)c).getEntity().getGuid() == entity.getGuid());
        componentsToRender.removeIf((c) -> c.getEntity().getGuid() == entity.getGuid());
    }

    /**
     * Each tick it updates the values of the simulation
     * @param deltaTime delta time
     */
    private void tick(double deltaTime) {
        componentsToTick.stream().filter((t) -> !((Component)t).isInitialized()).forEach(c -> ((Component)c).onInitialize());

        componentsToTick.forEach((t) -> t.onUpdate(deltaTime));

        ControlBarFXMLController.getInstance().onUpdate(deltaTime);
    }

    private void tickSelectionCheck() {
        if (!Input.isMouseButtonPressed(MouseButton.PRIMARY)) {
            return;
        }

        Vector2 worldCoordinates = Camera.getInstance().screenSpaceToWorldSpace(Input.getCurrentMouseScreenPosition());

        double closestDistance = Double.MAX_VALUE;
        Entity selectedEntity = null;

        for (Entity entity : Simulation.getInstance().getEntities()) {
            if (entity.getRenderer() instanceof PlanetRenderer) {
                double distance = entity.getTransform().getPosition().distanceTo(worldCoordinates);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    selectedEntity = entity;
                }
            }
        }

//        System.out.println("selected:");
//        System.out.println(selectedEntity);
        //System.out.println(selectedEntity.getTransform().getPosition());

//        System.out.println(worldCoordinates);
        //System.out.println("Dist: " + selectedEntity.getTransform().getPosition().distanceTo(worldCoordinates));
        //System.out.println("Tgt : " + ((PlanetRenderer) selectedEntity.getRenderer()).getDiameter() / 2);

        // Discard if we didn't click on the planet directly.
        if (selectedEntity != null && selectedEntity.getTransform().getPosition().distanceTo(worldCoordinates) > ((PlanetRenderer) selectedEntity.getRenderer()).getDiameter() / 2) {
            selectedEntity = null;
        }

        if (ControlBarFXMLController.getInstance().getSelectedEntity() != null) {
            ControlBarFXMLController.getInstance().getSelectedEntity().setSelected(false);
        }

        ControlBarFXMLController.getInstance().selectEntity(selectedEntity);

        if (selectedEntity != null) {
            selectedEntity.setSelected(true);
        }
    }

    /**
     * Clears the screen
     */
    private void clearScreen() {
        for (Canvas canvas : linkedSimulation.getCanvases()) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, linkedSimulation.getCanvasStack().getWidth(), linkedSimulation.getCanvasStack().getHeight());
        }

        var gc = linkedSimulation.getCanvases()[RenderLayers.SPACE_SIMULATION.ordinal()].getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, linkedSimulation.getCanvasStack().getWidth(), linkedSimulation.getCanvasStack().getHeight());
    }

    /**
     * Draws on the screen
     */
    private void draw() {
        Camera camera = Camera.getInstance();
        Vector2 cameraPos = camera.getTransform().getPosition().negate();

        for (int i = 0; i < linkedSimulation.getCanvases().length; i++) {
            if (!linkedSimulation.getActiveRenderLayers().contains(RenderLayers.values()[i])) {
                continue;
            }

            // Peak java moment, for some reason the 'i' above isn't immutable enough.
            int finalI = i;
            componentsToRender.forEach((r) -> {
                if (!linkedSimulation.getActiveRenderLayers().contains(r.getLayer())) {
                    return;
                }

                if (!camera.isInViewport(r.getEntity())) {
                    return;
                }

                GraphicsContext gc = linkedSimulation.getCanvases()[finalI].getGraphicsContext2D();
                correctForCameraPositionAndDraw(camera.getZoom(), cameraPos, gc, r::onDraw);
            });
        }

//        GraphicsContext debugCanvas = linkedSimulation.getCanvases()[RenderLayers.DEBUG.ordinal()].getGraphicsContext2D();
    }

    /**
     * A draw callback
     */
    private interface DrawCallback {
        void onDraw(GraphicsContext gc);
    }

    /**
     * Corrects the camera position and draws
     * @param zoom the zoom correction
     * @param position the position correction
     * @param graphicsContext the graphics context correction
     * @param callback the callback correction
     */
    private void correctForCameraPositionAndDraw(double zoom, Vector2 position, GraphicsContext graphicsContext, DrawCallback callback) {
        graphicsContext.save();
        graphicsContext.scale(zoom, zoom);
        graphicsContext.translate(position.getX(), position.getY());
        callback.onDraw(graphicsContext);
        graphicsContext.restore();
    }
}
