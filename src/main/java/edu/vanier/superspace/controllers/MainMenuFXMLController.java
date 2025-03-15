package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Application;
import edu.vanier.template.controllers.MainAppFXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView imgViewBackground;

    public void initialize() {
        btnQuit.setOnAction(event -> {
            //TODO: Add some animation to the button on hover
            Stage currentStage = (Stage) btnQuit.getScene().getWindow();

            logger.info("Closing the application...");
            currentStage.close();
        });

        btnAdd.setOnAction(event -> {
            //TODO: Implement the creation of a file for a simulation
            //TODO: Buttons for each simulation that the user has
            logger.info("Adding a simulation...");
        });
    }
}
