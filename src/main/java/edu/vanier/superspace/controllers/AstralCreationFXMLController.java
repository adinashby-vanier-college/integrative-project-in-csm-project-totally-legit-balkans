package edu.vanier.superspace.controllers;

import com.google.errorprone.annotations.FormatMethod;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.components.DebugCircleRenderer;
import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.BorderPaneAutomaticResizing;
import edu.vanier.superspace.utils.InputValidator;
import javafx.event.ActionEvent;
import edu.vanier.superspace.Application;
import edu.vanier.superspace.utils.FileHelper;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.border.Border;

/**
 * FXML Controller class for the astral creation scene.
 * Handles the user inputs for the creation of custom astral bodies.
 */
public class AstralCreationFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(AstralCreationFXMLController.class);

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

    @FXML
    private void onImageSelectorClicked() {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/main/resources/Sprites/Planets/"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        chooser.setTitle("Select Image File For Astral Body");
        File astralImage = chooser.showSaveDialog(Application.getPrimaryStage().getOwner());
        System.out.println(astralImage.getAbsolutePath());
        ImageView i = new ImageView(new Image("file:///" + astralImage.getAbsolutePath()));
        i.setFitHeight(90);
        i.setFitWidth(90);
        btnImageSelector.setText("");
        btnImageSelector.setGraphic(i);
        
    }
  
    public void addAstralBody() {
        //TODO: Check for the possible exceptions for each possible user input
        //TODO: Implement the dragging to the simulation of the astral body
        //TODO: Implement the creation of a new physics entity after exceptions are handled
        try {
//            mass = Double.parseDouble(txtFieldMass.getText());
//            speed = Double.parseDouble(txtFieldSpeed.getText());
//            description = txtFieldDescription.getText();
//            type = cmbType.valueProperty().get();
//            imagePath = btnImageSelector.getBackground().getImages().getFirst().getImage().getUrl();
        } catch (Exception e) {
            System.out.println("Exceptions to be handled...");
        }

        System.out.println("Dragged");
    }

    @FXML
    private void onDragEnd(DragEvent dragEvent) {
        Vector2 targetPosition = Vector2.of(dragEvent.getSceneX(), dragEvent.getSceneY()).subtract(BorderPaneAutomaticResizing.getInstance().topLeftCornerPositionOffset());

        Entity newEntity = new Entity();
        newEntity.addComponent(new Transform());
        newEntity.addComponent(new RigidBody());
        newEntity.addComponent(new DebugCircleRenderer());
        newEntity.register();
    }

    @FXML
    private void onSubmitDouble(ActionEvent event) {
        var field = (TextField)event.getSource();
        InputValidator.validateDouble(field);
        System.out.println(field.getUserData());
    }
}
