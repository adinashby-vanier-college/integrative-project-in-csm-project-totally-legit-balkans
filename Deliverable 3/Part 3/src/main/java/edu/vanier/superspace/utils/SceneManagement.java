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
/**
 * Class to manage the loading of scenes for different or new projects
 * @author mrcoc
 */
public class SceneManagement {
    public static void loadScene(Scenes scene) {
        SceneManagement.loadScene(scene, true);
    }
/**
 * Loads the selected scene, from the chosen FXML file.
 * @param scene
 * @param reload 
 */
    public static void loadScene(Scenes scene, boolean reload) {
        BorderPane root = new BorderPane();
        scene.getInitializer().initialize(root, reload);
        ThemeChanger changer = new ThemeChanger();

        Scene loadedScene = new Scene(root, RenderDimensions.getApplicationTargetWidth(), RenderDimensions.getApplicationTargetHeight());
        Input.initialize(loadedScene);
        changer.changeTheme(loadedScene);
        
        
        Application.getPrimaryStage().setScene(loadedScene);
        Application.getPrimaryStage().sizeToScene();
    }

    @SneakyThrows
    public static Parent loadPartial(Partials partial) {
        return loadPartial(partial, null);
    }

    @SneakyThrows
    public static Parent loadPartial(Partials partial, Object controller) {
        return FXMLHelper.loadFXML(partial.getFilepath(), controller);
    }
}
