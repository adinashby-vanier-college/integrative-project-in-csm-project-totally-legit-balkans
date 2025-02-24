package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML controller class for the primary stage scene.
 *
 * @author Adriano
 */
public class MainAppFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);

    @FXML
    private Pane mainPane;

    @FXML
    public void initialize() {
        mainPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/images/mainMenuBackground.png").toExternalForm()),null,null,null,null)));
        logger.info("Initializing MainAppController...");
    }
    
}
