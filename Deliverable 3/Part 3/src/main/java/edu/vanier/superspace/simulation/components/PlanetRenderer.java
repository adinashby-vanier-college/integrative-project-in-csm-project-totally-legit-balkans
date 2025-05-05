package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Tickable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Renders the actual planets upon the simulation and is a component
 */
@Getter
public class PlanetRenderer extends Renderer implements Tickable {
    private static final Logger logger = LoggerFactory.getLogger(PlanetRenderer.class);

    @ToSerialize
    private String imagePath;
    @ToSerialize @Setter
    private double diameter;

    private Image image;

    /**
     * Default constructor
     */
    public PlanetRenderer() {
        super(RenderLayers.SPACE_SIMULATION);
    }

    /**
     * Parameterized constructor
     * @param diameter diameter of the planet
     * @param imagePath path of the image for the planet
     */
    public PlanetRenderer(double diameter, String imagePath) {
        this();
        this.diameter = diameter;
        this.imagePath = imagePath;
    }

    /**
     * Applied when the planet is initialized
     */
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

    /**
     * Draws the image to the graphics context of the simulation
     * @param gc the graphics context
     */
    @Override
    public void onDraw(GraphicsContext gc) {
        if (image == null) {
            return;
        }

        Vector2 position = getEntity().getTransform().getPosition();
        Vector2 halfSize = estimateSize().divide(2);
        gc.drawImage(image, position.getX() - halfSize.getX(), position.getY() - halfSize.getY(), diameter, diameter);
//        Vector2 pos = getEntity().getTransform().getPosition();
//        gc.setStroke(Color.GREEN);
//        gc.setLineWidth(100);
//        gc.strokeLine(pos.getX() - 500, pos.getY() - 500, pos.getX() + 500, pos.getY() + 500);
//        gc.strokeLine(pos.getX() - 500, pos.getY() + 500, pos.getX() + 500, pos.getY() - 500);
    }

    /**
     * Estimates the size
     * @return size estimate as a vector value
     */
    @Override
    public Vector2 estimateSize() {
        return Vector2.of(diameter, diameter);
    }

    /**
     * Sets the image path of a planet
     * @param imagePath the path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        try {
            image = new Image(imagePath);
        } catch (Exception e) {
            logger.error("Unable to load image for PlanetRenderer at path: " + imagePath);
            logger.error(e.getMessage());
        }
    }

    /**
     * On update due to Tickable interface
     * @param deltaTime delta time
     */
    @Override
    public void onUpdate(double deltaTime) {

    }
}
