package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import edu.vanier.superspace.utils.SimulationSettings;
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
    private Button btnSimImagePath;
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
    @FXML
    private RadioButton rdbSmall;
    @FXML
    private RadioButton rdbFontMedium;
    @FXML
    private RadioButton rdbBig;
    @FXML
    private RadioButton rdbCalibri;
    @FXML
    private RadioButton rdbArial;
    @FXML
    private RadioButton rdbDubai;
    
    public void initialize() {
        logger.info("Initializing SettingsFXMLController...");
    }

    @FXML
    private void onBackButtonClicked() {
        logger.info("Back to the simulation...");
        SceneManagement.loadScene(Scenes.SIMULATION, false);
    }

    @FXML @SneakyThrows
    private void onMenuImagePathClicked() {
        logger.info("Setting image path for menu...");
        File chosenFile = this.chooseFile();
        SimulationSettings.getInstance().setMenuBackground(new Image("file:///" + chosenFile.getAbsolutePath()));
    }

    @FXML @SneakyThrows
    private void onSimImagePathClicked() {
        logger.info("Setting image path for simulation...");
        File chosenFile = this.chooseFile();
        
    }

    @FXML
    private void onRdbDashedClicked() {
        astralPathStyle(true, false, false);
    }

    @FXML
    private void onRdbDottedClicked() {
        astralPathStyle(false, true, false);
    }

    @FXML
    private void onRdbFullClicked() {
        astralPathStyle(false, false, true);
    }

    @FXML
    private void onRdbThinClicked() {
        astralPathsThickness(true, false, false);
    }

    @FXML
    private void onRdbThicknessMediumClicked() {
        astralPathsThickness(false, true, false);
    }

    @FXML
    private void onRdbThick() {
        astralPathsThickness(false, false, true);
    }

    @FXML
    private void onRdbLightClicked() {
        theme(true, false);
    }

    @FXML
    private void onRdbDarkClicked() {
        theme(false, true);
    }

    @FXML
    private void onRdbSmallClicked() {
        fontSize(true, false, false);
    }

    @FXML
    private void onRdbFontMediumClicked() {
        fontSize(false, true, false);
    }

    @FXML
    private void onRdbBigClicked() {
        fontSize(false, false, true);
    }

    @FXML
    private void onRdbCalibriClicked() {
        font(true, false, false);
    }

    @FXML
    private void onRdbArialClicked() {
        font(false, true, false);
    }

    @FXML
    private void onRdbDubaiClicked() {
        font(false, false, true);
    }

    private void astralPathStyle(boolean dashed, boolean dotted, boolean full) {
        rdbDashed.setSelected(dashed);
        rdbDotted.setSelected(dotted);
        rdbFull.setSelected(full);
    }

    private void astralPathsThickness(boolean thin, boolean medium, boolean thick) {
        rdbThin.setSelected(thin);
        rdbThicknessMedium.setSelected(medium);
        rdbThick.setSelected(thick);
    }

    private void theme(boolean light, boolean dark) {
        rdbLight.setSelected(light);
        rdbDark.setSelected(dark);
    }

    private void fontSize(boolean small, boolean medium, boolean big) {
        rdbSmall.setSelected(small);
        rdbFontMedium.setSelected(medium);
        rdbBig.setSelected(big);
    }

    private void font(boolean calibri, boolean arial, boolean dubai) {
        rdbCalibri.setSelected(calibri);
        rdbArial.setSelected(arial);
        rdbDubai.setSelected(dubai);
    }
    
    @SneakyThrows
    public File chooseFile(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a New Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
            return fileChooser.showOpenDialog(Application.getPrimaryStage());
        } catch (Exception e) {
            logger.info("No image chosen...");
        }
        
        return null;
    }
}
