package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.controllers.AstralCreationFXMLController;
import edu.vanier.superspace.controllers.ControlBarFXMLController;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.*;
import edu.vanier.superspace.utils.BorderPaneAutomaticResizing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;

import javax.naming.event.NamespaceChangeListener;

/**
 * The camera for the simulation, which is what the user can see
 */
@Getter
public class Camera extends Entity implements Tickable {
    private static final double timeToDoubleZoom = 1;

    @Getter @Setter
    private static Camera instance;

    @Getter @Setter @ToSerialize
    private double zoom = 1;
    @ToSerialize
    private Vector2 viewport;

    /**
     * Default constructor
     */
    public Camera() {
        super();
        Camera.instance = this;
    }

    /**
     * Registers the camera in the simulation
     */
    @Override
    public void register() {
        Simulation.getInstance().registerCamera(this);
    }

    /**
     * Implements Tickable so on each update it checks the user inputs for camera movement.
     * @param deltaTime delta time
     */
    @Override
    public void onUpdate(double deltaTime) {
        recalculateViewport();

        if (AstralCreationFXMLController.getInstance().isSelected()) {
            return;
        }

        double xVelocity = 0;
        double yVelocity = 0;

        if (Input.isKeyHeld(KeyCode.W)) {
            yVelocity += 20 * deltaTime;
        }

        if (Input.isKeyHeld(KeyCode.S)) {
            yVelocity -= 20 * deltaTime;
        }

        if (Input.isKeyHeld(KeyCode.D)) {
            xVelocity += 20 * deltaTime;
        }

        if (Input.isKeyHeld(KeyCode.A)) {
            xVelocity -= 20 * deltaTime;
        }

        Vector2 mousePos =  Input.getCurrentMouseCanvasPosition();
        if (Input.getScrollDistance() < 0) {
            Vector2 start = screenSpaceToWorldSpace(mousePos);

            zoom *= -Input.getScrollDistance() * deltaTime * timeToDoubleZoom;
            ControlBarFXMLController.getInstance().getZoomSlider().setValue(zoom);
            recalculateViewport();

            Vector2 end = screenSpaceToWorldSpace(mousePos);
            this.getTransform().getPosition().addAssign(start.subtract(end));
        } else if (Input.getScrollDistance() > 0) {
            Vector2 start = screenSpaceToWorldSpace(mousePos);

            zoom /= Input.getScrollDistance() * deltaTime * timeToDoubleZoom;
            ControlBarFXMLController.getInstance().getZoomSlider().setValue(zoom);
            recalculateViewport();

            Vector2 end = screenSpaceToWorldSpace(mousePos);
            this.getTransform().getPosition().addAssign(start.subtract(end));
        }

        zoom = Math.clamp(zoom, 0.1, 10);

        Vector2 mouseMovement = Vector2.zero();
        if (Input.isMouseButtonHeld(MouseButton.PRIMARY)) {
            mouseMovement = Input.mouseDelta().divide(zoom).negate();
        }

        Vector2 velocity = Vector2.of(xVelocity, yVelocity);
        transform.getPosition().addAssign(velocity).addAssign(mouseMovement);
    }

    /**
     * Recalculates the value of the viewport
     */
    private void recalculateViewport() {
        AnchorPane drawPane = ((AnchorPane) BorderPaneAutomaticResizing.getInstance().getPane().getCenter());
        viewport = Vector2.of(drawPane.getWidth(), drawPane.getHeight()).multiply(zoom);
    }

    /**
     * Translates simulation dimensions to real world dimensions
     * @param screenSpace the simulation dimensions
     * @return dimensions as vector value
     */
    public Vector2 screenSpaceToWorldSpace(Vector2 screenSpace) {
        Vector2 realDimensions = Vector2.of(screenSpace.getX() / zoom, screenSpace.getY() / zoom);
        return this.transform.getPosition().add(realDimensions);
    }

    /**
     * Checks if an entity is in the viewport of the simulation
     * @param entity an entity
     * @return true or false
     */
    public boolean isInViewport(Entity entity) {
        return true;
//        Vector2 cameraPos = transform.getPosition();
//        Vector2 cameraSize = viewport;
//        Vector2 entitySize = entity.getRenderer().estimateSize();
//        Vector2 entityPosition = entity.getTransform().getPosition().add(entitySize.divide(2).negateOnRenderAxis());
//        return intersects(cameraPos, cameraSize, entityPosition, entitySize);
    }

    /**
     * Checks the intersection of 2 different entities based on their position and scale.
     * @param pos1 position of first entity
     * @param size1 scale of first entity
     * @param pos2 position of second entity
     * @param size2 scale of second entity
     * @return true or false
     */
    private boolean intersects(Vector2 pos1, Vector2 size1, Vector2 pos2, Vector2 size2) {
        return pos1.getX() < pos2.getX() + size2.getX()
                && pos1.getX() + size1.getX() > pos2.getX()
                && pos1.getY() < pos2.getY() + size2.getY()
                && pos1.getY() + size1.getY() > pos2.getY();
    }
}
