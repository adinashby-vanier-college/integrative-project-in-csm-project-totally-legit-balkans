package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class SceneManagement {
    public static void loadScene(Scenes scene) {
        SceneManagement.loadScene(scene, true);
    }

    public static void loadScene(Scenes scene, boolean reload) {
        BorderPane root = new BorderPane();
        scene.getInitializer().initialize(root, reload);

        Scene loadedScene = new Scene(root, RenderDimensions.getApplicationTargetWidth(), RenderDimensions.getApplicationTargetHeight());
        Application.getPrimaryStage().setScene(loadedScene);
        Application.getPrimaryStage().sizeToScene();
    }

    public static Parent loadPartial(Partials partial) {
        return FXMLHelper.loadFXML(partial.getFilepath());
    }
}
