package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

/**
 * Renderer that is used to render planets on the simulation
 */
@Getter
public abstract class Renderer extends Component {
    private final RenderLayers layer;

    /**
     * Parameterized constructor
     * @param layer the layer to render upon
     */
    public Renderer(RenderLayers layer) {
        this.layer = layer;
    }

    /**
     * Draws to a graphics context
     * @param gc the graphics context
     */
    public abstract void onDraw(GraphicsContext gc);

    /**
     * Estimates the size
     * @return the size estimate as a vector value
     */
    public abstract Vector2 estimateSize();
}
