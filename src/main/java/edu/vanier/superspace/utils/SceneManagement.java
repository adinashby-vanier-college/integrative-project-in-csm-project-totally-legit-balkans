package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManagement {
    public static void loadScene(Scenes sceneEnum) {
        Parent parent = FXML.loadFXML(sceneEnum.getFilepath());
        Scene scene = new Scene(parent, RenderDimensions.getApplicationTargetWidth(), RenderDimensions.getApplicationTargetHeight());
        Application.getPrimaryStage().setScene(scene);
        Application.getPrimaryStage().sizeToScene();
    }
}
