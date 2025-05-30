package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.simulation.components.TrailRenderer;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import edu.vanier.superspace.utils.SimulationSettings;
import edu.vanier.superspace.utils.ThemeChanger;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class for the settings scene.
 * Handles the settings for the simulation that are set by the user.
 */
public class SettingsFXMLController {
    //TODO: Have default settings
    private final static Logger logger = LoggerFactory.getLogger(SettingsFXMLController.class);
    @FXML
    private Button btnBack;
    @FXML
    private Button btnMenuImagePath;
    @FXML
    private RadioButton rdbDashed;
    @FXML
    private RadioButton rdbDotted;
    @FXML
    private RadioButton rdbFull;
    @FXML
    private RadioButton rdbThin;
    @FXML
    private RadioButton rdbThicknessMedium;
    @FXML
    private RadioButton rdbThick;
    @FXML
    private RadioButton rdbLight;
    @FXML
    private RadioButton rdbDark;
    
    /**
     * FXML Controller initializer
     */
    public void initialize() {
        logger.info("Initializing SettingsFXMLController...");
    }

    /**
     * Brings you back to the simulation.
     */
    @FXML
    private void onBackButtonClicked() {
        logger.info("Back to the simulation...");
        SceneManagement.loadScene(Scenes.SIMULATION, false);
    }

    /**
     * Changes the image for the main menu
     */
    @FXML @SneakyThrows
    private void onMenuImagePathClicked() {
        logger.info("Setting image path for menu...");
        File chosenFile = this.chooseFile();

        if (chosenFile != null) {
            SimulationSettings.getInstance().setMenuBackground(new Image("file:///" + chosenFile.getAbsolutePath()));
        }
    }

    /**
     * Changes the disabled values of the radio buttons on click.
     */
    @FXML
    private void onRdbDashedClicked() {
        astralPathStyle(true, false, false);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbDottedClicked() {
        astralPathStyle(false, true, false);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbFullClicked() {
        astralPathStyle(false, false, true);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbThinClicked() {
        astralPathsThickness(true, false, false);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbThicknessMediumClicked() {
        astralPathsThickness(false, true, false);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbThick() {
        astralPathsThickness(false, false, true);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbLightClicked() {
        theme(true, false);
    }

    /**
     * Changes the disabled values of the radio buttons click.
     */
    @FXML
    private void onRdbDarkClicked() {
        theme(false, true);
    }

    /**
     * Changes the disabled values of the radio buttons.
     */
    private void astralPathStyle(boolean dashed, boolean dotted, boolean full) {
        rdbDashed.setSelected(dashed);
        rdbDotted.setSelected(dotted);
        rdbFull.setSelected(full);
        
        if(rdbFull.isSelected()){
            TrailRenderer.skip=0;
        }else if(rdbDotted.isSelected()){
            TrailRenderer.skip = 2;
        }else if(rdbDashed.isSelected()){
            TrailRenderer.skip = 5;
        }
        
    }

    /**
     * Changes the disabled values of the radio buttons.
     */
    private void astralPathsThickness(boolean thin, boolean medium, boolean thick) {
        rdbThin.setSelected(thin);
        rdbThicknessMedium.setSelected(medium);
        rdbThick.setSelected(thick);
        
        if(rdbThin.isSelected()){
            TrailRenderer.thickness = 1;
        }else if(rdbThicknessMedium.isSelected()){
            TrailRenderer.thickness = 5;
        }else if(rdbThick.isSelected()){
            TrailRenderer.thickness = 10;
        }
        
    }

    /**
     * Changes the disabled values of the radio buttons.
     */
    private void theme(boolean light, boolean dark) {
        rdbLight.setSelected(light);
        rdbDark.setSelected(dark);
        ThemeChanger changer = new ThemeChanger();
        
        rdbLight.getScene().getStylesheets().clear();
        
        if(rdbDark.isSelected()){
            ThemeChanger.light=false;
        }else if(rdbLight.isSelected()){
            ThemeChanger.light=true;
        }
        
        changer.changeTheme(rdbLight.getScene());
        
    }

    /**
     * Opens the file explorer and user can pick an image.
     */
    @SneakyThrows
    public File chooseFile(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a New Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
            File file = fileChooser.showOpenDialog(Application.getPrimaryStage());

            if (file != null) {
                return file;
            }
        } catch (Exception e) {
            logger.info("No image chosen...");
        }
        
        return null;
    }
}
