package edu.vanier.superspace.simulation;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.Renderer;
import javafx.scene.canvas.Canvas;
import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Vector;

public class Simulation {
    @Getter
    private static Simulation instance = null;

    private final ArrayList<Entity> entities = new ArrayList<>();
    private String name;
    private final ArrayList<Renderer> renderers = new ArrayList<>();
    private final ArrayList<Canvas> canvases = new ArrayList<>();
    private final EnumSet<RenderLayers> activeRenderLayers = EnumSet.of(RenderLayers.LAYER1);

    private final SimulationTime clock = new SimulationTime();

    public Simulation() {
        if (Simulation.instance != null) {
            Simulation.instance.StopAndClose(true);
        }

        Simulation.instance = this;
    }

    public void Run() {

    }

    public void Stop() {

    }

    public void Step() {

    }

    public void onSceneClicked(Vector2 position) {

    }

    public void StopAndClose(boolean saveBeforeQuitting) {

    }
}
