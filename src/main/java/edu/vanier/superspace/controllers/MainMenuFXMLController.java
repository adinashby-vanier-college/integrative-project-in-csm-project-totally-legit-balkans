package edu.vanier.superspace.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class for the main menu scene.
 */
public class MainMenuFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(MainMenuFXMLController.class);
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView imgViewBackground;

    public void initialize() {
        logger.info("Initializing MainMenuFXMLController...");
    }

    @FXML
    private void onQuitButtonClicked() {
        //TODO: Add some animation to the button on hover
        Stage currentStage = (Stage) btnQuit.getScene().getWindow();

        logger.info("Closing the application...");
        currentStage.close();
    }

    @FXML
    private void onAddButtonClicked() {
        //TODO: Implement the creation of a file for a simulation
        //TODO: Buttons for each simulation that the user has
        logger.info("Adding a simulation...");
    }
}
