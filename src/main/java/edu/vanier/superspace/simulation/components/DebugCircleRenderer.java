package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Input;
import edu.vanier.superspace.simulation.Tickable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DebugCircleRenderer extends Renderer {
    private final double diameter = 1700 * 2;

    /**
     * Default constructor
     */
    public DebugCircleRenderer() {
        super(RenderLayers.DEBUG);
    }

    /**
     * Draws the circle to the simulation
     * @param gc the graphics context
     */
    @Override
    public void onDraw(GraphicsContext gc) {
        Transform tf = getEntity().getTransform();
        Vector2 pos = tf.getPosition();
        double radius = diameter / 2;
//        gc.setFill(Color.BLUE);
//        gc.fillOval(tf.getPosition().getX() - radius, tf.getPosition().getY() - radius, diameter, diameter);

        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        gc.strokeLine(pos.getX() - 5, pos.getY() - 5, pos.getX() + 5, pos.getY() + 5);
        gc.strokeLine(pos.getX() - 5, pos.getY() + 5, pos.getX() + 5, pos.getY() - 5);

//        System.out.println("drawn!");
    }

    /**
     * Estimates the size of the circle
     * @return vector value
     */
    @Override
    public Vector2 estimateSize() {
        return Vector2.of(diameter, diameter);
    }
}
