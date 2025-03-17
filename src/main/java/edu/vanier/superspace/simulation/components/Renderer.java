package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.dto.RenderLayers;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

public abstract class Renderer extends Component {
    @Getter
    private final RenderLayers layer;

    public Renderer(RenderLayers layer) {
        this.layer = layer;
    }

    public abstract void onDraw(GraphicsContext gc);
}
