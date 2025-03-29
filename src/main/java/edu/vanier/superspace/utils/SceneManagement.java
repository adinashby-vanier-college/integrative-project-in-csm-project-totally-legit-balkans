package edu.vanier.superspace.utils;

import com.google.gson.annotations.SerializedName;
import edu.vanier.superspace.Application;
import edu.vanier.superspace.simulation.Input;
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
