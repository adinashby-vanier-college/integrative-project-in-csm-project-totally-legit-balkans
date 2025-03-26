package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Main;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.components.DebugCircleRenderer;
import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.BorderPaneAutomaticResizing;
import edu.vanier.superspace.utils.InputValidator;
import edu.vanier.superspace.utils.Partials;
import edu.vanier.superspace.utils.SceneManagement;
import javafx.event.ActionEvent;
import edu.vanier.superspace.Application;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;

/**
 * FXML Controller class for the astral creation scene.
 * Handles the user inputs for the creation of custom astral bodies.
 */
public class AstralCreationFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(AstralCreationFXMLController.class);
    private boolean isPopupOpen = false;

    @FXML
    private Button btnImageSelector;
    @FXML
    private TextField txtFieldPath;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private ComboBox<String> cmbType;
    @FXML
    private TextField txtFieldRadius;
    @FXML
    private TextField txtFieldMass;
    @FXML
    private TextField txtFieldVelocity;
    @FXML
    private ComboBox<String> cmbDirection;
    @FXML
    private TextField txtFieldDistance;
    @FXML
    private ComboBox<String> cmbReference;

    public void initialize() {
        logger.info("Initializing AstralCreationFXMLController...");

        cmbType.getItems().addAll(
                "Gaseous",
                "Ice",
                "Ocean",
                "Terrestrial"
        );

        cmbDirection.getItems().addAll(
                "Clockwise",
                "Counter-clockwise"
        );
    }

//    @FXML
//    private void onMouseDraggedOverImageSelector() {
//        addAstralBody();
//    }

//    @FXML
//    private void onImageSelectorClicked() {
//        FileChooser chooser = new FileChooser();
//        chooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/main/resources/Sprites/Planets/"));
//        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
//        chooser.setTitle("Select Image File For Astral Body");
//        File astralImage = chooser.showSaveDialog(Application.getPrimaryStage().getOwner());
//        System.out.println(astralImage.getAbsolutePath());
//        ImageView i = new ImageView(new Image("file:///" + astralImage.getAbsolutePath()));
//        i.setFitHeight(90);
//        i.setFitWidth(90);
//        btnImageSelector.setText("");
//        btnImageSelector.setGraphic(i);
//
//    }

    public void enableControls() {
        txtFieldPath.setDisable(false);
        txtFieldName.setDisable(false);
        txtAreaDescription.setDisable(false);
        cmbType.setDisable(false);
        txtFieldRadius.setDisable(false);
        txtFieldMass.setDisable(false);
        txtFieldVelocity.setDisable(false);
        cmbDirection.setDisable(false);
    }

    public void disableControls() {
        txtFieldPath.setDisable(true);
        txtFieldName.setDisable(true);
        txtAreaDescription.setDisable(true);
        cmbType.setDisable(true);
        txtFieldRadius.setDisable(true);
        txtFieldMass.setDisable(true);
        txtFieldVelocity.setDisable(true);
        cmbDirection.setDisable(true);
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

//    @FXML
//    private void onDragEnd(DragEvent dragEvent) {
//        Vector2 targetPosition = Vector2.of(dragEvent.getSceneX(), dragEvent.getSceneY()).subtract(BorderPaneAutomaticResizing.getInstance().topLeftCornerPositionOffset());
//
//        Entity newEntity = new Entity();
//        newEntity.addComponent(new Transform());
//        newEntity.addComponent(new RigidBody());
//        newEntity.addComponent(new DebugCircleRenderer());
//        newEntity.register();
//    }

    @FXML
    private void onAction(MouseEvent event) throws IOException {
        System.out.println("Clicked!");

        ContextMenu cm = new ContextMenu();
        MenuItem addNew = new MenuItem("Add new");
        MenuItem mercury = new MenuItem("Mercury");
        MenuItem venus = new MenuItem("Venus");
        MenuItem earth = new MenuItem("Earth");

        ImageView addNewIm = new ImageView(new Image(getClass().getResource("/fxml/images/AddNew.png").toExternalForm()));
        addNewIm.setFitWidth(32);
        addNewIm.setFitHeight(32);
        addNew.setGraphic(addNewIm);
        ImageView mercuryIm = new ImageView(new Image(getClass().getResource("/Sprites/Planets/Mercury.png").toExternalForm()));
        mercuryIm.setFitHeight(32);
        mercuryIm.setFitWidth(32);
        mercury.setGraphic(mercuryIm);
        ImageView venusIm = new ImageView(new Image(getClass().getResource("/Sprites/Planets/Venus.png").toExternalForm()));
        venusIm.setFitHeight(32);
        venusIm.setFitWidth(32);
        venus.setGraphic(venusIm);
        ImageView earthIm = new ImageView(new Image(getClass().getResource("/Sprites/Planets/Earth.png").toExternalForm()));
        earthIm.setFitHeight(32);
        earthIm.setFitWidth(32);
        earth.setGraphic(earthIm);

        cm.getItems().addAll(addNew, mercury, venus, earth);
        btnImageSelector.setContextMenu(cm);
        btnImageSelector.getScene().getStylesheets().add(getClass().getResource("/css/ContextMenuStyle.css").toExternalForm());

//        if (!isPopupOpen) {
//            isPopupOpen = true;
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(Main.class.getResource("/fxml/astralCreationPopup.fxml"));
//            Scene scene = new Scene(root);
//
//            stage.setScene(scene);
//            stage.setX(event.getSceneX());
//            stage.setY(event.getSceneY());
//            stage.setAlwaysOnTop(true);
//            stage.show();
//
//            stage.setOnCloseRequest(event1 -> {
//                logger.info("Closing the popup...");
//                isPopupOpen = false;
//            });
//        }
    }

    @FXML
    private void onSubmitDouble(ActionEvent event) {
        var field = (TextField)event.getSource();
        InputValidator.validateDouble(field);
        System.out.println(field.getUserData());
    }
}
