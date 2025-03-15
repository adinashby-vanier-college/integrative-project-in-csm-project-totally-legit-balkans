package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.controllers.MainMenuFXMLController;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManagement {
    public static void loadScene(Scenes sceneEnum) {
        Parent parent = FXML.loadFXML(sceneEnum.getFilepath(), new MainMenuFXMLController());
        Scene scene = new Scene(parent, RenderDimensions.getApplicationTargetWidth(), RenderDimensions.getApplicationTargetHeight());
        Application.getPrimaryStage().setScene(scene);
        Application.getPrimaryStage().sizeToScene();
    }
}
