package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DebugCircleRenderer extends Renderer {
    private final double radius = 100;

    public DebugCircleRenderer() {
        super(RenderLayers.DEBUG);
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        Transform tf = getEntity().getTransform();
        double halfRadius = radius / 2;
        gc.setFill(Color.BLUE);
        gc.fillOval(tf.getPosition().getX() - halfRadius, tf.getPosition().getY() - halfRadius, radius, radius);
    }

    @Override
    public Vector2 estimateSize() {
        return Vector2.of(radius, radius);
    }
}
