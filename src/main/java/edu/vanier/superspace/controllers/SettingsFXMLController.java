package edu.vanier.superspace.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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

        btnBack.setOnAction(event -> {
            logger.info("Back to the simulation...");
        });

        btnMenuImagePath.setOnAction(event -> {
            logger.info("Setting image path for menu...");
        });

        btnSimImagePath.setOnAction(event -> {
            logger.info("Setting image path for simulation...");
        });

        rdbDashed.setOnAction(event -> {
            astralPathStyle(true, false, false);
        });

        rdbDotted.setOnAction(event -> {
            astralPathStyle(false, true, false);
        });

        rdbFull.setOnAction(event -> {
            astralPathStyle(false, false, true);
        });

        rdbThin.setOnAction(event -> {
            astralPathsThickness(true, false, false);
        });

        rdbThicknessMedium.setOnAction(event -> {
            astralPathsThickness(false, true, false);
        });

        rdbThick.setOnAction(event -> {
            astralPathsThickness(false, false, true);
        });

        rdbLight.setOnAction(event -> {
            theme(true, false);
        });

        rdbDark.setOnAction(event -> {
            theme(false, true);
        });

        rdbSmall.setOnAction(event -> {
            fontSize(true, false, false);
        });

        rdbFontMedium.setOnAction(event -> {
            fontSize(false, true, false);
        });

        rdbBig.setOnAction(event -> {
            fontSize(false, false, true);
        });

        rdbCalibri.setOnAction(event -> {
            font(true, false, false);
        });

        rdbArial.setOnAction(event -> {
            font(false, true, false);
        });

        rdbDubai.setOnAction(event -> {
            font(false, false, true);
        });
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
}
