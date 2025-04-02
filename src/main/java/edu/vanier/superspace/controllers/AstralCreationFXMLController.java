package edu.vanier.superspace.controllers;

import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.components.Camera;
import edu.vanier.superspace.simulation.components.PlanetRenderer;
import edu.vanier.superspace.simulation.components.RigidBody;
import edu.vanier.superspace.simulation.components.Transform;
import edu.vanier.superspace.utils.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class for the astral creation scene.
 * Handles the user inputs for the creation of custom astral bodies.
 */
public class AstralCreationFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(AstralCreationFXMLController.class);
    @Getter
    private static AstralCreationFXMLController instance;
    private boolean contextMenuStyled = false;
    private double dragXMouseAnchor;
    private double dragYMouseAnchor;
    private double dragLayoutX;
    private double dragLayoutY;
    private Entity body;
    private AstralBody selectedAstralBody;
    private boolean isEmpty = true;
    @Getter
    private boolean isSelected = false;
    private UserCatalog userCatalog;

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

    public AstralCreationFXMLController() {
        instance = this;
    }

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

    public void addAstralBody() {}

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

    }

    @FXML
    private void onPaneMouseEntered(MouseEvent event) throws IOException {
        if (!contextMenuStyled) {
            btnImageSelector.getScene().getStylesheets().add(getClass().getResource("/css/ContextMenuStyle.css").toExternalForm());
            userCatalog = Simulation.getInstance().getUserCatalog();
            loadContextMenu();
            btnImageSelector.setContextMenu(contextMenu);
            contextMenuStyled = true;
        }
    }

    @FXML
    private void onButtonMousePressed(MouseEvent event) {
        dragXMouseAnchor = event.getX();
        dragYMouseAnchor = event.getY();
    }

    @FXML
    private void onButtonMouseDragged(MouseEvent event) {
        isSelected = true;
        dragLayoutX = event.getSceneX();
        dragLayoutY = event.getSceneY();
        Vector2 dragDelta = Vector2.of(dragLayoutX, dragLayoutY);
        Vector2 finalPlanetPosition = Camera.getInstance().screenSpaceToWorldSpace(dragDelta);

        if (body == null) {
            if (!isEmpty) {
                Entity newEntity = new Entity();
                newEntity.addComponent(new Transform());
                newEntity.addComponent(new RigidBody(selectedAstralBody.getMass()));
                PlanetRenderer planetRenderer = new PlanetRenderer(selectedAstralBody.getRadius() * 2,
                        selectedAstralBody.getPath());
                System.out.println(selectedAstralBody.getPath());
                System.out.println(selectedAstralBody.getMass());
                newEntity.addComponent(planetRenderer);
                newEntity.getTransform().setPosition(finalPlanetPosition);
                newEntity.register();
                body = newEntity;
            }
        } else {
            body.getTransform().setPosition(finalPlanetPosition);
        }
    }

    @FXML
    private void onButtonMouseReleased(MouseEvent event) {
        if (body != null) {
            resetAstralCreation();
            System.out.println(dragLayoutX);
            System.out.println(dragLayoutY);
        }
        isSelected = false;
    }

    @FXML
    private void onBtnResetPressed(ActionEvent event) {
        resetAstralCreation();
    }

    @FXML
    private void onBtnRemovePressed(ActionEvent event) {

    }

    private String addIconToPath(String path) {
        return path.substring(0, path.length() - 4) + "Icon.png";
    }

    private void loadContextMenu() throws IOException {
        ContextMenu cm = new ContextMenu();

        MenuItem addNew = new MenuItem("Add new");
        ImageView addNewImage = new ImageView(new Image(getClass().getResource("/fxml/Images/AddNew.png").toExternalForm()));
        addNewImage.setFitWidth(32);
        addNewImage.setFitHeight(32);
        addNew.setGraphic(addNewImage);
        cm.getItems().add(addNew);

        for (int i = 0; i < userCatalog.getCatalog().size(); i++) {
            AstralBody astralBody  = userCatalog.getCatalog().get(i);
            String menuItemName = astralBody.getName().substring(0, 1).toUpperCase() +
                    astralBody.getName().substring(1).toLowerCase();

            MenuItem menuItem = new MenuItem(menuItemName);
            ImageView imageView = new ImageView(new Image(addIconToPath(astralBody.getPath())));
            ImageView buttonImage = new ImageView(new Image(addIconToPath(astralBody.getPath())));
            imageView.setFitHeight(32);
            imageView.setFitWidth(32);
            menuItem.setGraphic(imageView);
            cm.getItems().add(menuItem);

            menuItem.setOnAction(event -> {
                buttonImage.setFitHeight(32);
                buttonImage.setFitWidth(32);
                updatePresetValues(astralBody, buttonImage, astralBody.isPreset());
                isEmpty = false;
                selectedAstralBody = astralBody;
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
        selectedAstralBody = null;
    }

    @FXML
    private void onSubmitDouble(ActionEvent event) {
        var field = (TextField)event.getSource();
        InputValidator.validateDoubleWithUserData(field);
        System.out.println(field.getUserData());
    }

    private void updatePresetValues(AstralBody astralBody, Node graphic, boolean isPreset) {
        enableControls();
        txtFieldPath.setText(astralBody.getPath());
        txtFieldName.setText(astralBody.getName());
        txtAreaDescription.setText(astralBody.getDescription());
        cmbType.setValue(astralBody.getType());
        txtFieldMass.setText(String.valueOf(astralBody.getMass()));
        txtFieldRadius.setText(String.valueOf(astralBody.getRadius()));

        graphic.setScaleX(3);
        graphic.setScaleY(3);
        btnImageSelector.setText("");
        btnImageSelector.setGraphic(graphic);
        btnReset.setDisable(false);
        btnRemove.setDisable(isPreset);
        dragLayoutX = 0;
        dragLayoutY = 0;
    }
}
