package edu.vanier.superspace.utils;

import com.google.gson.annotations.SerializedName;
import edu.vanier.superspace.Application;
import edu.vanier.superspace.controllers.ControlBarFXMLController;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Input;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.components.Camera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import lombok.SneakyThrows;

public class SceneManagement {
    public static void loadScene(Scenes scene) {
        SceneManagement.loadScene(scene, true);
    }

    public static void loadScene(Scenes scene, boolean reload) {
        BorderPane root = new BorderPane();
        scene.getInitializer().initialize(root, reload);

        root.setOnMouseClicked(event -> {
            if (Simulation.getInstance() != null) {
                Entity closestEntity = null;
                double closestDistance = 100;
                for (Entity entity : Simulation.getInstance().getEntities()) {
                    Vector2 entityPosition = Vector2.of(entity.getTransform().getPosition().getX(), entity.getTransform().getPosition().getY());
                    Vector2 mousePosition = Vector2.of(event.getSceneX(), event.getSceneY());
                    double distance = entityPosition.distanceTo(mousePosition);

                    if (closestDistance > distance) {
                        closestEntity = entity;
                        closestDistance = distance;
                    }
                }

                if (closestEntity != null) {
                    ControlBarFXMLController.getInstance().updateSelectedEntity(closestEntity);
                }
            }
        });

        Scene loadedScene = new Scene(root, RenderDimensions.getApplicationTargetWidth(), RenderDimensions.getApplicationTargetHeight());
        Input.initialize(loadedScene);
        Application.getPrimaryStage().setScene(loadedScene);
        Application.getPrimaryStage().sizeToScene();
    }

    @SneakyThrows
    public static Parent loadPartial(Partials partial) {
        return FXMLHelper.loadFXML(partial.getFilepath());
    }
}
