package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.controllers.AstralCreationFXMLController;
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

@Getter
public class Camera extends Entity implements Tickable {
    private static final double timeToDoubleZoom = 1;

    @Getter @Setter
    private static Camera instance;

    @Getter @Setter @ToSerialize
    private double zoom = 1;
    @ToSerialize
    private Vector2 viewport;

    public Camera() {
        super();
        Camera.instance = this;
    }

    @Override
    public void register() {
        Simulation.getInstance().registerCamera(this);
    }

    @Override
    public void onUpdate(double deltaTime) {
        AnchorPane drawPane = ((AnchorPane) BorderPaneAutomaticResizing.getInstance().getPane().getCenter());
        viewport = Vector2.of(drawPane.getWidth(), drawPane.getHeight()).multiply(zoom);

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

        if (Input.getScrollDistance() < 0) {
            zoom *= -Input.getScrollDistance() * deltaTime * timeToDoubleZoom;
        } else if (Input.getScrollDistance() > 0) {
            zoom /= Input.getScrollDistance() * deltaTime * timeToDoubleZoom;
        }

        Vector2 mouseMovement = Vector2.zero();
        if (Input.isMouseButtonHeld(MouseButton.PRIMARY)) {
            mouseMovement = Input.mouseDelta().negateOnRenderAxis();
        }

        Vector2 velocity = Vector2.of(xVelocity, yVelocity);
        transform.getPosition().addAssign(velocity).addAssign(mouseMovement);
    }

    public Vector2 screenSpaceToWorldSpace(Vector2 screenSpace) {
        Vector2 realDimensions = screenSpace.divide(zoom);
        return transform.getPosition().add(realDimensions);
    }

    public boolean isInViewport(Entity entity) {
        return true;
//        Vector2 cameraPos = transform.getPosition();
//        Vector2 cameraSize = viewport;
//        Vector2 entitySize = entity.getRenderer().estimateSize();
//        Vector2 entityPosition = entity.getTransform().getPosition().add(entitySize.divide(2).negateOnRenderAxis());
//        return intersects(cameraPos, cameraSize, entityPosition, entitySize);
    }

    private boolean intersects(Vector2 pos1, Vector2 size1, Vector2 pos2, Vector2 size2) {
        return pos1.getX() < pos2.getX() + size2.getX()
                && pos1.getX() + size1.getX() > pos2.getX()
                && pos1.getY() < pos2.getY() + size2.getY()
                && pos1.getY() + size1.getY() > pos2.getY();
    }
}
