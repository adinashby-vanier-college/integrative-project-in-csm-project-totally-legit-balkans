package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.PhysicsEntity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.Tickable;
import edu.vanier.superspace.utils.BorderPaneAutomaticResizing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;

import javax.naming.event.NamespaceChangeListener;

@Getter
public class Camera extends Entity implements Tickable {
    @Getter @Setter
    private static Camera instance;

    @Setter
    private double zoom = 1;
    private Vector2 viewport;

    public Camera() {
        super();
        Camera.instance = this;
        AnchorPane drawPane = ((AnchorPane) BorderPaneAutomaticResizing.getInstance().getPane().getCenter());
        viewport = Vector2.of(drawPane.getWidth(), drawPane.getHeight());
    }

    @Override
    public void register() {
        Simulation.getInstance().registerCamera(this);
    }

    @Override
    public void onUpdate(double deltaTime) {
        transform.getPosition().addAssign(Vector2.of(0, 0));
    }

    public Vector2 screenSpaceToWorldSpace(Vector2 screenSpace) {
        Vector2 realDimensions = screenSpace.divide(zoom);
        return realDimensions.add(transform.getPosition());
    }

    public boolean isInViewport(Entity entity) {
        return intersects(transform.getPosition(), viewport, entity.getTransform().getPosition().subtract(entity.getRenderer().estimateSize().divide(2)), entity.getRenderer().estimateSize());
    }

    private boolean intersects(Vector2 pos1, Vector2 size1, Vector2 pos2, Vector2 size2) {
        return pos1.getX() < pos2.getX() + size2.getX()
                && pos1.getX() + size1.getX() > pos2.getX()
                && pos1.getY() < pos2.getY() + size2.getY()
                && pos1.getY() + size1.getY() > pos2.getY();
    }
}
