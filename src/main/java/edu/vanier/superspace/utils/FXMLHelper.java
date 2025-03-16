package edu.vanier.superspace.utils;

import edu.vanier.superspace.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FXMLHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(FXMLHelper.class);

    public static Parent loadFXML(String path) {
        return loadFXML(path, null);
    }

    @NonNull
    public static Parent loadFXML(String path, Object controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));

        // If a controller is provided for certain specific cases, we want to set it
        if (controller != null) {
            fxmlLoader.setController(controller);
        }

        try {
            return fxmlLoader.load();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }
}
