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

        double deltaTime;
        if (stepOnce) {
            deltaTime = 0.1;
            stepOnce = false;
        } else {
            deltaTime = (double)elapsedNanoseconds / 1e9;
        }

        if (running) {
            tick(deltaTime);
        }

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
                gc.save();
                gc.translate(cameraPos.getX(), cameraPos.getY());
                r.onDraw(gc);
                gc.restore();
            });
        }
    }

}
