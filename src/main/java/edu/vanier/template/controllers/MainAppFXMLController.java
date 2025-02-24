package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
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
