package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML controller class for the primary stage scene.
 *
 * @author frostybee
 */
public class MainAppFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);

    @FXML
    private Pane mainPane;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
    }
    
}
