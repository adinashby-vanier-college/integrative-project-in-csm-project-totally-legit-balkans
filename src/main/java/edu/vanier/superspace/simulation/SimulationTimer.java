package edu.vanier.superspace.simulation;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.simulation.components.Renderer;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class SimulationTimer extends AnimationTimer {
    private final ArrayList<Tickable> componentsToTick = new ArrayList<>();
    private final ArrayList<Renderer> componentsToRender = new ArrayList<>();

    @Setter
    private Simulation linkedSimulation;
    private long lastUpdateTime = 0;

    @Override
    public void handle(long now) {
        if (lastUpdateTime == 0) {
            lastUpdateTime = now;
            return;
        }

        long elapsedNanoseconds = lastUpdateTime - now;
        lastUpdateTime = now;

        double deltaTime = (double)elapsedNanoseconds / 1e9;

        tick(deltaTime);
        draw();
    }

    private void tick(double deltaTime) {
        componentsToTick.forEach((t) -> t.onUpdate(deltaTime));
    }

    private void draw() {
        for (int i = 0; i < linkedSimulation.getCanvases().size(); i++) {
            if (!linkedSimulation.getActiveRenderLayers().contains(RenderLayers.values()[i])) {
                continue;
            }

            // Peak java moment, for some reason the 'i' above isn't immutable enough.
            int finalI = i;
            componentsToRender.forEach((r) -> {
                if (!linkedSimulation.getActiveRenderLayers().contains(r.getLayer())) {
                    return;
                }

                r.onDraw(linkedSimulation.getCanvases().get(finalI).getGraphicsContext2D());
            });
        }
    }

}
