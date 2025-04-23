package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Tickable;
import javafx.scene.canvas.GraphicsContext;

public class TrailRenderer extends Renderer implements Tickable  {
    public TrailRenderer() {
        super(RenderLayers.TRAILS);
    }

    @Override
    public void onUpdate(double deltaTime) {

    }

    @Override
    public void onDraw(GraphicsContext gc) {

    }

    @Override
    public Vector2 estimateSize() {
        return null;
    }
}
