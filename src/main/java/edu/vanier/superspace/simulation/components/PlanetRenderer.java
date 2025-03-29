package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class PlanetRenderer extends Renderer {
    private static final Logger logger = LoggerFactory.getLogger(PlanetRenderer.class);

    @ToSerialize
    private String imagePath;
    @ToSerialize @Setter
    private double diameter;

    private Image image;

    public PlanetRenderer() {
        super(RenderLayers.SPACE_SIMULATION);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        try {
            image = new Image(imagePath);
        } catch (Exception e) {
            logger.error("Unable to load image for PlanetRenderer at path: " + imagePath);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        if (image == null) {
            return;
        }

        Vector2 position = getEntity().getTransform().getPosition();
        Vector2 halfSize = estimateSize().divide(2);
        gc.drawImage(image, position.getX() - halfSize.getX(), position.getY() - halfSize.getY(), diameter, diameter);
    }

    @Override
    public Vector2 estimateSize() {
        return Vector2.of(diameter, diameter);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        try {
            image = new Image(imagePath);
        } catch (Exception e) {
            logger.error("Unable to load image for PlanetRenderer at path: " + imagePath);
            logger.error(e.getMessage());
        }
    }
}
