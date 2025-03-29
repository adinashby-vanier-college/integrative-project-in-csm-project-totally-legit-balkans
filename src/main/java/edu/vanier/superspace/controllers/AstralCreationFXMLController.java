package edu.vanier.superspace.controllers;

import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.components.DebugCircleRenderer;
import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.*;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class for the astral creation scene.
 * Handles the user inputs for the creation of custom astral bodies.
 */
public class AstralCreationFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(AstralCreationFXMLController.class);
    private boolean contextMenuStyled = false;
    private double dragXMouseAnchor;
    private double dragYMouseAnchor;
    private double dragLayoutX;
    private double dragLayoutY;
    private Entity body;
    private boolean isEmpty = true;
    private boolean isPreset;

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

        loadContextMenu();
        btnImageSelector.setContextMenu(contextMenu);
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
    }

    @FXML
    private void onButtonContextMenuRequested(ContextMenuEvent event) throws IOException {
//        contextMenu = loadedContextMenu;
//        btnImageSelector.setContextMenu(contextMenu);
//        System.out.println("hi");
        reloadContextMenu();
        //TODO: Implement a way to reload the images, maybe clone the loadedContextMenu and assign it here everytime
        // cm is requested. (Maybe not required)
    }

    private void reloadContextMenu() {
        MenuItem hello = new MenuItem("Hello");
        contextMenu.getItems().add(hello);
    }

    @FXML
    private void onPaneMouseEntered(MouseEvent event) {
        if (!contextMenuStyled) {
            btnImageSelector.getScene().getStylesheets().add(getClass().getResource("/css/ContextMenuStyle.css").toExternalForm());
            contextMenuStyled = true;
            System.out.println("styled");
        }
    }

    @FXML
    private void onButtonMousePressed(MouseEvent event) {
        dragXMouseAnchor = event.getX();
        dragYMouseAnchor = event.getY();
    }

    @FXML
    private void onButtonMouseDragged(MouseEvent event) {
        dragLayoutX = event.getSceneX() - dragXMouseAnchor;
        dragLayoutY = event.getSceneY() - dragYMouseAnchor;

        if (body == null) {
            if (!isEmpty) {
                Entity newEntity = new Entity();
                newEntity.addComponent(new Transform());
                newEntity.addComponent(new RigidBody());
                newEntity.addComponent(new DebugCircleRenderer());
                newEntity.getTransform().setPosition(new Vector2(dragLayoutX, dragLayoutY));
                newEntity.register();
                body = newEntity;
            }
        } else {
            body.getTransform().setPosition(new Vector2(dragLayoutX, dragLayoutY));
        }
    }

    @FXML
    private void onButtonMouseReleased(MouseEvent event) {
        if (body != null) {
            resetAstralCreation();
            System.out.println(dragLayoutX);
            System.out.println(dragLayoutY);
        }
    }

    @FXML
    private void onBtnResetPressed(ActionEvent event) {
        resetAstralCreation();
    }

    @FXML
    private void onBtnRemovePressed(ActionEvent event) {

    }

    private void loadContextMenu() throws IOException {
        ContextMenu cm = new ContextMenu();

        for (int i = 0; i < Presets.values().length; i++) {
            Presets preset = Presets.values()[i];
            String menuItemName = preset.getName().substring(0, 1).toUpperCase() +
                    preset.getName().substring(1).toLowerCase();

            MenuItem menuItem = new MenuItem(menuItemName);
            ImageView imageView = new ImageView(new Image(getClass().getResource("/Sprites/Planets/"
                    + menuItemName + ".png").toExternalForm()));
            ImageView buttonImage = new ImageView(new Image(getClass().getResource("/Sprites/Planets/"
                    + menuItemName + ".png").toExternalForm()));
            imageView.setFitHeight(32);
            imageView.setFitWidth(32);
            menuItem.setGraphic(imageView);
            cm.getItems().add(menuItem);

            menuItem.setOnAction(event -> {
                buttonImage.setFitHeight(32);
                buttonImage.setFitWidth(32);
                updatePresetValues(preset, buttonImage);
                isEmpty = false;
            });
        }

        contextMenu = cm;
    }

    @FXML
    private void onMouseClickedImage(MouseEvent event) throws IOException {
        System.out.println("Clicked!");
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
        btnReset.setDisable(true);
        btnRemove.setDisable(true);
        isEmpty = true;
        body = null;
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
