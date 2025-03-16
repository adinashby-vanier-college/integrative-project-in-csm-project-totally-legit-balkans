package edu.vanier.superspace.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class for the astral creation scene.
 * Handles the user inputs for the creation of custom astral bodies.
 */
public class AstralCreationFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(AstralCreationFXMLController.class);
    private String imagePath;
    private double mass;
    private double speed;
    private String description;
    private String type;
    @FXML
    private Button btnImageSelector;
    @FXML
    private TextField txtFieldMass;
    @FXML
    private TextField txtFieldSpeed;
    @FXML
    private TextField txtFieldDescription;
    @FXML
    private ComboBox<String> cmbType;

    public void initialize() {
        logger.info("Initializing AstralCreationFXMLController...");

        cmbType.getItems().addAll(
                "Gaseous",
                "Ice",
                "Ocean",
                "Terrestrial"
        );
    }

    @FXML
    private void onMouseDraggedOverImageSelector() {
        addAstralBody();
    }

    public void addAstralBody() {
        //TODO: Check for the possible exceptions for each possible user input
        //TODO: Implement the dragging to the simulation of the astral body
        //TODO: Implement the creation of a new physics entity after exceptions are handled
        try {
            mass = Double.parseDouble(txtFieldMass.getText());
            speed = Double.parseDouble(txtFieldSpeed.getText());
            description = txtFieldDescription.getText();
            type = cmbType.valueProperty().get();
            imagePath = btnImageSelector.getBackground().getImages().getFirst().getImage().getUrl();
        } catch (Exception e) {
            System.out.println("Exceptions to be handled...");
        }
    }
}
