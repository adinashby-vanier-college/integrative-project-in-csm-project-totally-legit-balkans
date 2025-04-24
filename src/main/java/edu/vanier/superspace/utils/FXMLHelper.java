package edu.vanier.superspace.utils;

import edu.vanier.superspace.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper with the FXML
 */
public class FXMLHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(FXMLHelper.class);

    /**
     * Loads a fxml based on its path and without a controller
     * @param path the path of the fxml file
     * @return Parent
     * @throws Exception occurs if the fxml file is not found
     */
    public static Parent loadFXML(String path) throws Exception {
        return loadFXML(path, null);
    }

    /**
     * Loads a fxml file with an instance of a controller
     * @param path the path of the file
     * @param controller the controller to be assigned with
     * @return Parent
     * @throws Exception occurs if fxml file is not found
     */
    @NonNull
    public static Parent loadFXML(String path, Object controller) throws Exception {
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

        throw new Exception("Invalid FXML at: " + path);
    }
}
