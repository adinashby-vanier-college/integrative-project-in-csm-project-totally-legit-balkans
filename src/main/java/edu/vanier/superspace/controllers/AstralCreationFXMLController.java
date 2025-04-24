package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.SimulationTimer;
import edu.vanier.superspace.simulation.components.*;
import edu.vanier.superspace.utils.*;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
    private double dragLayoutX;
    private double dragLayoutY;
    private Entity body;
    private AstralBody selectedAstralBody;
    private boolean isEmpty = true;
    @Getter
    private boolean isSelected = false;
    private boolean isVerified = false;
    private UserCatalog userCatalog;
    private Entity entity;
    private int numOfBodies = 0;
//    private boolean hasAttractor;

    @FXML
    private Button btnReset;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnAddPreset;
    @FXML
    private RadioButton rdbAttractor;
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
    private TextField txtFieldVelocityMagnitude;
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

    public void enableControls() {
        txtFieldPath.setDisable(false);
        txtFieldName.setDisable(false);
        txtAreaDescription.setDisable(false);
        cmbType.setDisable(false);
        txtFieldRadius.setDisable(false);
        txtFieldMass.setDisable(false);
        txtFieldVelocityMagnitude.setDisable(false);
        cmbDirection.setDisable(false);
        txtFieldDistance.setDisable(false);
        cmbReference.setDisable(false);
        rdbAttractor.setDisable(false);

        /* Used for one attractor only
            if (!hasAttractor) {
                rdbAttractor.setDisable(false);
            }
         */
    }

    public void disableControls() {
        txtFieldPath.setDisable(true);
        txtFieldName.setDisable(true);
        txtAreaDescription.setDisable(true);
        cmbType.setDisable(true);
        txtFieldRadius.setDisable(true);
        txtFieldMass.setDisable(true);
        txtFieldVelocityMagnitude.setDisable(true);
        cmbDirection.setDisable(true);
        txtFieldDistance.setDisable(true);
        cmbReference.setDisable(true);
        rdbAttractor.setDisable(true);
    }

    public boolean addAstralBody() {
        verifySelectedAstralBody();
        if (isVerified) {
            AstralBody astralBody;
            try {
                String name = selectedAstralBody.getName();
                String description = selectedAstralBody.getDescription();
                String type = selectedAstralBody.getType();
                double radius = selectedAstralBody.getRadius();
                double mass = selectedAstralBody.getMass();
                String path = selectedAstralBody.getPath();

                astralBody = new AstralBody(name, description, type, radius, mass, path, false);
                Simulation.getInstance().getUserCatalog().addToCatalog(astralBody);
                return true;
            } catch (Exception e) {
                System.out.println("What went wrong??");
            }
        }
        return false;
    }

    public void addToContextMenu(AstralBody astralBody) {
        MenuItem menuItem = new Menu(astralBody.getName());
        ImageView imageView = new ImageView(new Image(astralBody.getPath()));
        ImageView buttonImage = new ImageView(new Image(astralBody.getPath()));
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);
        buttonImage.setFitHeight(32);
        buttonImage.setFitWidth(32);
        menuItem.setGraphic(imageView);
        contextMenu.getItems().add(menuItem);

        menuItem.setOnAction(event -> {
            updateUserPresetValues(astralBody, buttonImage);
            isEmpty = false;
            selectedAstralBody = astralBody;
        });
    }

    @FXML
    private void onPaneMouseEntered(MouseEvent event) throws IOException {
        if (!contextMenuStyled) {
            btnImageSelector.getScene().getStylesheets().add(getClass().getResource("/css/ContextMenuStyle.css").toExternalForm());
            userCatalog = Simulation.getInstance().getUserCatalog();
            loadContextMenu();
            btnImageSelector.setContextMenu(contextMenu);
            contextMenuStyled = true;

//            Entity newEntity = new Entity();
//            newEntity.addComponent(new Transform());
//            newEntity.getTransform().setPosition(Vector2.of(500, 500));
//            newEntity.addComponent(new RigidBody(200));
//            PlanetRenderer planetRenderer = new PlanetRenderer(20000 * 2,
//                    Assets.EARTH.getFilePath());
//            newEntity.addComponent(planetRenderer);
//            newEntity.register();
//            entity = newEntity;
        }
    }

    @FXML
    private void onButtonMouseDragged(MouseEvent event) {
        isSelected = true;
        dragLayoutX = event.getSceneX();
        dragLayoutY = event.getSceneY();
        Vector2 dragDelta = Vector2.of(dragLayoutX, dragLayoutY);
        Vector2 finalPlanetPosition = Camera.getInstance().screenSpaceToWorldSpace(dragDelta);

        verifySelectedAstralBody();
        System.out.println(isVerified);
        if (isVerified) {
            if (body == null) {
                if (!isEmpty) {
                    double velocityMagnitude = Double.parseDouble(txtFieldVelocityMagnitude.getText());
                    Vector2 velocity = Vector2.of(0, 0);

                    if (cmbDirection.getValue() != null && !rdbAttractor.isSelected()) {
                        if (cmbDirection.getValue().equals("Clockwise")) {
                            velocityMagnitude = velocityMagnitude / 2;
                            velocity = Vector2.of(-1 * velocityMagnitude, -1 * velocityMagnitude);
                        } else if (cmbDirection.getValue().equals("Counter-Clockwise")) {
                            velocityMagnitude = velocityMagnitude / 2;
                            velocity = Vector2.of(-1 * velocityMagnitude, velocityMagnitude);
                        }
                    }

                    Entity newEntity = new Entity();
                    newEntity.addComponent(new Transform());
                    newEntity.addComponent(new RigidBody(selectedAstralBody.getMass()));
                    newEntity.addComponent(new TrailRenderer());
                    System.out.println(selectedAstralBody.getRadius() * 2);
                    PlanetRenderer planetRenderer = new PlanetRenderer(selectedAstralBody.getRadius() * 2,
                            selectedAstralBody.getPath());
                    newEntity.addComponent(planetRenderer);
                    newEntity.getTransform().setPosition(finalPlanetPosition);
                    newEntity.setAstralBody(selectedAstralBody);
                    newEntity.register();
                    body = newEntity;
                    newEntity.getRigidBody().setVelocity(velocity);

                    Simulation.getInstance().Step();

                    if (rdbAttractor.isSelected()) {
                        body.getRigidBody().setAttractor(true);
//                        if (!hasAttractor) {
//                            hasAttractor = true;
//                            body.getRigidBody().setAttractor(true);
//                        }
                    }
                }
            } else {
                body.getTransform().setPosition(finalPlanetPosition);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Astral Creation Warning");
            alert.setContentText("Missing Astral Vody Values");
            
            alert.showAndWait();
        }
    }

    private void verifySelectedAstralBody() {
    try {
        double radius = Double.parseDouble(txtFieldRadius.getText());
        double mass = Double.parseDouble(txtFieldMass.getText());
        double velocity = Double.parseDouble(txtFieldVelocityMagnitude.getText());
        String path = txtFieldPath.getText();
        String name = txtFieldName.getText();
        String type = cmbType.getValue();
        String direction = cmbDirection.getValue();

        String errorMessage = null;
        
        if (mass <= 0.0) {
            errorMessage = "- Mass must be greater than 0\n";
        }
        else if (radius <= 0.0) {
            errorMessage = "- Radius must be greater than 0\n";
        }
        else if (velocity < 0) {
            errorMessage = "- Velocity cannot be negative\n";
        }
        else if (rdbAttractor.isSelected() && velocity != 0) {
            errorMessage = "- Attractor must have zero velocity\n";
        }
        else if (path.isEmpty()) {
            errorMessage = "- Image path is required\n";
        }
        else if (name.isEmpty()) {
            errorMessage = "- Name is required\n";
        }
        else if (type == null) {
            errorMessage = "- Type must be selected\n";
        }
        else if (direction == null && !rdbAttractor.isSelected()) {
            errorMessage = "- Direction must be selected\n";
        }

        if (errorMessage != null) {
            isVerified = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please fix the following issues in the Astral Creation Menu:");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        } else {
            isVerified = true;
            if (!selectedAstralBody.isPreset()) {
                selectedAstralBody.setName(txtFieldName.getText());
                selectedAstralBody.setDescription(txtAreaDescription.getText());
                selectedAstralBody.setType(cmbType.getValue());
                selectedAstralBody.setRadius(Double.parseDouble(txtFieldRadius.getText()));
                selectedAstralBody.setMass(Double.parseDouble(txtFieldMass.getText()));
                selectedAstralBody.setPath(txtFieldPath.getText());
            }
        }
    } catch (NumberFormatException e) {
        isVerified = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Numerical fields contain invalid values");
        alert.setContentText("Please enter valid numbers for radius, mass, and velocity.");
        alert.showAndWait();
    }
}

    @FXML
    private void onButtonMouseReleased(MouseEvent event) {
        if (body != null) {
            resetAstralCreation();
        }
        isSelected = false;
    }

    @FXML
    private void onBtnResetPressed(ActionEvent event) {
        resetAstralCreation();
    }

    @FXML
    private void onBtnRemovePressed(ActionEvent event) {
        if (!selectedAstralBody.isPreset()) {
            Simulation.getInstance().getUserCatalog().removeFromCatalog(selectedAstralBody);

            for (int i = 0; i < contextMenu.getItems().size(); i++) {
                MenuItem menuItem = contextMenu.getItems().get(i);

                if (menuItem.getText().equals(selectedAstralBody.getName())) {
                    contextMenu.getItems().remove(menuItem);
                }
            }

            resetAstralCreation();
        }
    }

    @FXML
    private void onBtnAddPreset(ActionEvent event) {
        if (addAstralBody()) {
            resetAstralCreation();
        }
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

        addNew.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/main/resources/Sprites/Planets/"));
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
            chooser.setTitle("Select Image File For Astral Body");
            File astralImage = chooser.showOpenDialog(Application.getPrimaryStage().getOwner());

            if (astralImage == null) {
                System.out.println("nothing happens");
            } else {
                ImageView i = new ImageView(new Image("file:///" + astralImage.getAbsolutePath()));
                i.setFitWidth(32);
                i.setFitHeight(32);
                isEmpty = false;
                selectedAstralBody = new AstralBody();
                selectedAstralBody.setPath("file:///" + astralImage.getAbsolutePath());
                updateNewAstralBody(i);
            }
        });

        // For the presets
        for (int i = 0; i < userCatalog.getCatalog().size(); i++) {
            AstralBody astralBody  = userCatalog.getCatalog().get(i);
            String menuItemName = astralBody.getName().substring(0, 1).toUpperCase() +
                    astralBody.getName().substring(1).toLowerCase();

            MenuItem menuItem = new MenuItem(menuItemName);
            ImageView imageView = new ImageView(new Image(addIconToPath(astralBody.getPath())));
            ImageView buttonImage = new ImageView(new Image(addIconToPath(astralBody.getPath())));
            imageView.setFitHeight(32);
            imageView.setFitWidth(32);
            buttonImage.setFitHeight(32);
            buttonImage.setFitWidth(32);
            menuItem.setGraphic(imageView);
            cm.getItems().add(menuItem);

            menuItem.setOnAction(event -> {
                updatePresetValues(astralBody, buttonImage, astralBody.isPreset());
                isEmpty = false;
                selectedAstralBody = astralBody;
            });
        }

        contextMenu = cm;
    }

    private void resetAstralCreation() {
        txtFieldPath.setText("");
        txtFieldRadius.setText("");
        txtFieldMass.setText("");
        txtFieldName.setText("");
        txtFieldDistance.setText("");
        txtAreaDescription.setText("");
        txtFieldVelocityMagnitude.setText("");
        cmbDirection.getSelectionModel().clearSelection();
        cmbReference.getSelectionModel().clearSelection();
        cmbType.getSelectionModel().clearSelection();

        disableControls();
        btnImageSelector.setText("Choose Planet");
        btnImageSelector.setGraphic(null);
        btnReset.setDisable(true);
        btnRemove.setDisable(true);
        btnAddPreset.setDisable(true);
        isEmpty = true;
        body = null;
        selectedAstralBody = null;
        isVerified = false;
        rdbAttractor.setSelected(false);
    }

    @FXML
    private void onSubmitDouble(ActionEvent event) {
        var field = (TextField)event.getSource();
        InputValidator.validateDoubleWithUserData(field);
    }

    private void updateNewAstralBody(Node graphic) {
        enableControls();
        graphic.setScaleX(3);
        graphic.setScaleY(3);

        txtFieldPath.setText(selectedAstralBody.getPath());
        btnImageSelector.setText("");
        btnImageSelector.setGraphic(graphic);
        btnReset.setDisable(false);
        btnAddPreset.setDisable(false);
        selectedAstralBody.setPreset(false);
    }

    private void updateUserPresetValues(AstralBody astralBody, Node graphic) {
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
        btnRemove.setDisable(false);
        btnAddPreset.setDisable(false);
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
        btnRemove.setDisable(!isPreset);
    }
}
