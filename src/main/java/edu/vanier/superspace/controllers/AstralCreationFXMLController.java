package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Main;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.components.DebugCircleRenderer;
import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.*;
import javafx.event.ActionEvent;
import edu.vanier.superspace.Application;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    @FXML
    private Button btnReset;
    @FXML
    private Button btnRemove;
    @FXML
    private ContextMenu contextMenu;
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

    public void initialize() throws IOException {
        logger.info("Initializing AstralCreationFXMLController...");

        cmbType.getItems().addAll(
                "Gaseous",
                "Ice",
                "Ocean",
                "Terrestrial",
                "Star"
        );

        cmbDirection.getItems().addAll(
                "Clockwise",
                "Counter-clockwise"
        );
    }


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
        txtFieldDistance.setDisable(false);
        cmbReference.setDisable(false);
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
        txtFieldDistance.setDisable(true);
        cmbReference.setDisable(true);
    }

    public void addAstralBody() {
        //TODO: Check for the possible exceptions for each possible user input
        //TODO: Implement the dragging to the simulation of the astral body
        //TODO: Implement the creation of a new physics entity after exceptions are handled
        //TODO: Add it to an arraylist of current astral bodies in the simulation
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
    private void onBtnResetPressed(ActionEvent event) {
        resetAstralCreation();
    }

    @FXML
    private void onBtnRemovePressed(ActionEvent event) {

    }

    @FXML
    private void onMouseClickedImage(MouseEvent event) throws IOException {
        System.out.println("Clicked!");

        ContextMenu cm = new ContextMenu();
        MenuItem addNew = new MenuItem("Add new");
        MenuItem mercury = new MenuItem("Mercury");
        MenuItem venus = new MenuItem("Venus");
        MenuItem earth = new MenuItem("Earth");
        MenuItem moon = new MenuItem("Moon");
        MenuItem mars = new MenuItem("Mars");
        MenuItem jupiter = new MenuItem("Jupiter");
        MenuItem uranus = new MenuItem("Uranus");
        MenuItem neptune =  new MenuItem("Neptune");
        MenuItem callisto =  new MenuItem("Callisto");
        MenuItem europa = new MenuItem("Europa");
        MenuItem io = new MenuItem("Io");
        MenuItem pluto = new MenuItem("Pluto");

        ImageView addNewIm = new ImageView(new Image(getClass().getResource("/fxml/images/AddNew.png").toExternalForm()));
        addNewIm.setFitWidth(32);
        addNewIm.setFitHeight(32);
        addNew.setGraphic(addNewIm);
        ImageView mercuryIm = new ImageView(new Image(Presets.getMercuryPath()));
        mercuryIm.setFitHeight(32);
        mercuryIm.setFitWidth(32);
        mercury.setGraphic(mercuryIm);
        ImageView venusIm = new ImageView(new Image(Presets.getVenusPath()));
        venusIm.setFitHeight(32);
        venusIm.setFitWidth(32);
        venus.setGraphic(venusIm);
        ImageView earthIm = new ImageView(new Image(Presets.getEarthPath()));
        earthIm.setFitHeight(32);
        earthIm.setFitWidth(32);
        earth.setGraphic(earthIm);
        ImageView moonIm = new ImageView(new Image(Presets.getMoonPath()));
        moonIm.setFitHeight(32);
        moonIm.setFitWidth(32);
        moon.setGraphic(moonIm);
        ImageView marsIm = new ImageView(new Image(Presets.getMarsPath()));
        marsIm.setFitHeight(32);
        marsIm.setFitWidth(32);
        mars.setGraphic(marsIm);
        ImageView jupiterIm = new ImageView(new Image(Presets.getJupiterPath()));
        jupiterIm.setFitHeight(32);
        jupiterIm.setFitWidth(32);
        jupiter.setGraphic(jupiterIm);
        ImageView uranusIm = new ImageView(new Image(Presets.getUranusPath()));
        uranusIm.setFitHeight(32);
        uranusIm.setFitWidth(32);
        uranus.setGraphic(uranusIm);
        ImageView neptuneIm = new ImageView(new Image(Presets.getNeptunePath()));
        neptuneIm.setFitHeight(32);
        neptuneIm.setFitWidth(32);
        neptune.setGraphic(neptuneIm);
        ImageView callistoIm = new ImageView(new Image(Presets.getCallistoPath()));
        callistoIm.setFitHeight(32);
        callistoIm.setFitWidth(32);
        callisto.setGraphic(callistoIm);
        ImageView europaIm = new ImageView(new Image(Presets.getEuropaPath()));
        europaIm.setFitHeight(32);
        europaIm.setFitWidth(32);
        europa.setGraphic(europaIm);
        ImageView ioIm = new ImageView(new Image(Presets.getIOPath()));
        ioIm.setFitHeight(32);
        ioIm.setFitWidth(32);
        io.setGraphic(ioIm);
        ImageView plutoIm = new ImageView(new Image(Presets.getPlutoPath()));
        plutoIm.setFitHeight(32);
        plutoIm.setFitWidth(32);
        pluto.setGraphic(plutoIm);

        btnImageSelector.setContextMenu(contextMenu);
        btnImageSelector.getScene().getStylesheets().add(getClass().getResource("/css/ContextMenuStyle.css").toExternalForm());
        cm.getItems().addAll(addNew, mercury, venus, earth, moon, mars, jupiter, uranus, neptune, callisto, europa, io, pluto);

        earth.setOnAction(event1 -> {
            updatePresetValues(Presets.EARTH, earthIm);
        });

        venus.setOnAction(event1 -> {
            updatePresetValues(Presets.VENUS, venusIm);
        });

        mercury.setOnAction(event1 -> {
            updatePresetValues(Presets.MERCURY, mercuryIm);
        });

        mars.setOnAction(event1 -> {
            updatePresetValues(Presets.MARS, marsIm);
        });

        moon.setOnAction(event1 -> {
            updatePresetValues(Presets.MOON, moonIm);
        });

        jupiter.setOnAction(event1 -> {
            updatePresetValues(Presets.JUPITER, jupiterIm);
        });

        uranus.setOnAction(event1 -> {
            updatePresetValues(Presets.URANUS, uranusIm);
        });

        neptune.setOnAction(event1 -> {
            updatePresetValues(Presets.NEPTUNE, neptuneIm);
        });

        callisto.setOnAction(event1 -> {
            updatePresetValues(Presets.CALLISTO, callistoIm);
        });

        europa.setOnAction(event1 -> {
            updatePresetValues(Presets.EUROPA, europaIm);
        });

        io.setOnAction(event1 -> {
            updatePresetValues(Presets.IO, ioIm);
        });

        pluto.setOnAction(event1 -> {
            updatePresetValues(Presets.PLUTO, plutoIm);
        });

        contextMenu = cm;
    }

    private void resetAstralCreation() {
        txtFieldPath.setText("");
        txtFieldRadius.setText("");
        txtFieldMass.setText("");
        txtFieldName.setText("");
        txtFieldDistance.setText("");
        txtAreaDescription.setText("");
        txtFieldVelocity.setText("");
        cmbDirection.setValue("");
        cmbReference.setValue("");
        cmbType.setValue("");

        disableControls();
        btnImageSelector.setText("Choose Planet");
        btnImageSelector.setGraphic(null);
    }

    @FXML
    private void onSubmitDouble(ActionEvent event) {
        var field = (TextField)event.getSource();
        InputValidator.validateDouble(field);
        System.out.println(field.getUserData());
    }

    private void updatePresetValues(Presets preset, Node graphic) {
        enableControls();
        txtFieldPath.setText(preset.getPath());
        txtFieldName.setText(preset.getName());
        txtAreaDescription.setText(preset.getDescription());
        cmbType.setValue(preset.getType());
        txtFieldMass.setText(String.valueOf(preset.getMass()));
        txtFieldRadius.setText(String.valueOf(preset.getRadius()));

        graphic.setScaleX(3);
        graphic.setScaleY(3);
        btnImageSelector.setText("");
        btnImageSelector.setGraphic(graphic);
        btnReset.setDisable(false);
        btnRemove.setDisable(true);
    }
}
