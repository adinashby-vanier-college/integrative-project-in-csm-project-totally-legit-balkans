package edu.vanier.superspace.simulation;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.Camera;
import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.simulation.components.Renderer;
import edu.vanier.superspace.simulation.components.Transform;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class SimulationTimer extends AnimationTimer {
    @Getter
    private final ArrayList<Tickable> componentsToTick = new ArrayList<>();
    private final ArrayList<Renderer> componentsToRender = new ArrayList<>();

    @Setter
    private boolean running = true;
    private boolean stepOnce = false;

    @Setter
    private Simulation linkedSimulation;
    private long lastUpdateTime = 0;

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


        lastUpdateTime = now;

        double deltaTime = (double)elapsedNanoseconds / 1e9;

        if (stepOnce) {
            tick(0.1);
        }

        if (running) {
            tick(deltaTime);
        }

        Camera.getInstance().onUpdate(deltaTime);
        Input.update();
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
    }

    private void clearScreen() {
        for (Canvas canvas : linkedSimulation.getCanvases()) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, linkedSimulation.getCanvasStack().getWidth(), linkedSimulation.getCanvasStack().getHeight());
        }
    }

    private void draw() {
        Camera camera = Camera.getInstance();
        Vector2 cameraPos = camera.getTransform().getPosition().negateOnRenderAxis();

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

        GraphicsContext debugCanvas = linkedSimulation.getCanvases()[RenderLayers.DEBUG.ordinal()].getGraphicsContext2D();
//        correctForCameraPositionAndDraw(camera.getZoom(), Vector2.zero(), debugCanvas, (gc) -> {
//            gc.setStroke(Color.RED);
//            gc.setLineWidth(3);
//            gc.strokeRect(camera.getTransform().getPosition().getX(), camera.getTransform().getPosition().getY(), camera.getViewport().getX(), camera.getViewport().getY());
//        });
    }

    private interface DrawCallback {
        void onDraw(GraphicsContext gc);
    }

    private void correctForCameraPositionAndDraw(double zoom, Vector2 position, GraphicsContext graphicsContext, DrawCallback callback) {
        graphicsContext.save();
        graphicsContext.translate(position.getX(), position.getY());
        graphicsContext.scale(zoom, zoom);
        callback.onDraw(graphicsContext);
        graphicsContext.restore();
    }
}
