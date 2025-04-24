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

    public void step() {
        stepOnce = true;
    }

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
            tick(0.1);
            stepOnce = false;
        }

        if (running) {
            tick(deltaTime);
        }

        Camera.getInstance().onUpdate(deltaTime);
        Input.update();
        tickSelectionCheck();
        clearScreen();
        draw();
    }

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

    public void removeComponentsLinkedToEntity(Entity entity) {
        componentsToTick.removeIf((c) -> ((Component)c).getEntity().getGuid() == entity.getGuid());
        componentsToRender.removeIf((c) -> c.getEntity().getGuid() == entity.getGuid());
    }

    private void tick(double deltaTime) {
        componentsToTick.stream().filter((t) -> !((Component)t).isInitialized()).forEach(c -> ((Component)c).onInitialize());

        componentsToTick.forEach((t) -> t.onUpdate(deltaTime));

        ControlBarFXMLController.getInstance().onUpdate(deltaTime);

        ArrayList<RigidBody> rigidBodies = new ArrayList<>();
        for (Tickable component : componentsToTick) {
            if (component instanceof RigidBody rigidBody) {
                if (!rigidBodies.contains(rigidBody)) {
                    rigidBodies.add(rigidBody);
                }
            }
        }

        for (int i = 0; i < rigidBodies.size(); i++) {
            for (int j = 0; j < rigidBodies.size(); j++) {
                if (i != j) {
                    if (!rigidBodies.get(i).isAttractor())
                     rigidBodies.get(j).attract(rigidBodies.get(i).getEntity());
                }
            }
        }
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

        System.out.println("selected:");
        System.out.println(selectedEntity);
        System.out.println(selectedEntity.getTransform().getPosition());

        System.out.println(worldCoordinates);
        System.out.println("Dist: " + selectedEntity.getTransform().getPosition().distanceTo(worldCoordinates));
        System.out.println("Tgt : " + ((PlanetRenderer) selectedEntity.getRenderer()).getDiameter() / 2);

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

    private void clearScreen() {
        for (Canvas canvas : linkedSimulation.getCanvases()) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, linkedSimulation.getCanvasStack().getWidth(), linkedSimulation.getCanvasStack().getHeight());
        }

        var gc = linkedSimulation.getCanvases()[RenderLayers.SPACE_SIMULATION.ordinal()].getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, linkedSimulation.getCanvasStack().getWidth(), linkedSimulation.getCanvasStack().getHeight());
    }

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

    private interface DrawCallback {
        void onDraw(GraphicsContext gc);
    }

    private void correctForCameraPositionAndDraw(double zoom, Vector2 position, GraphicsContext graphicsContext, DrawCallback callback) {
        graphicsContext.save();
        graphicsContext.scale(zoom, zoom);
        graphicsContext.translate(position.getX(), position.getY());
        callback.onDraw(graphicsContext);
        graphicsContext.restore();
    }
}
