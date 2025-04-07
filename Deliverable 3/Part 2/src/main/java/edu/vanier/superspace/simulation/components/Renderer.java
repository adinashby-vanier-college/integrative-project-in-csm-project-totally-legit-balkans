package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

@Getter
public abstract class Renderer extends Component {
    private final RenderLayers layer;

    public Renderer(RenderLayers layer) {
        this.layer = layer;
    }

    public abstract void onDraw(GraphicsContext gc);

    public abstract Vector2 estimateSize();
}
