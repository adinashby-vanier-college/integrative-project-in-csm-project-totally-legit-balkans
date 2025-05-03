package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.components.Camera;
import edu.vanier.superspace.utils.SaveManager;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * FXML Controller class for the menu bar.
 */
public class MenuBarFXMLController {
    public MenuItem loadFromDiskButton;
    @FXML
    private MenuItem saveButton;
    @FXML
    private MenuItem saveAsButton;

    /**
     * Takes you back to the main menu.
     */
    @FXML
    private void onEdit(ActionEvent event) {
        SceneManagement.loadScene(Scenes.MAIN_MENU);
    }

    /**
     * Saves the simulation if it has already been saved as.
     */
    @FXML
    private void onSave(ActionEvent event) {
        SaveManager.save();
    }

    /**
     * Saves the simulation in the files of the program.
     */
    @FXML
    private void onSaveAs(ActionEvent event) {
        boolean success = SaveManager.saveAs();
        if (success) {
            saveButton.setDisable(false);
        }
    }

    /**
     * Loads a file from disk.
     */
    public void onLoadFromDisk(ActionEvent event) {
        SaveManager.load();
    }

    /**
     * Exits the applicaiton with no saving.
     */
    @FXML
    private void onExit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Opens the preferences menu.
     */
    @FXML
    private void onRequestPreferencesMenu(ActionEvent event) {
        SceneManagement.loadScene(Scenes.SETTINGS);
    }

    /**
     * Centers the camera so the user can come back to the original positionning.
     */
    @FXML
    private void onRequestCenterCamera(ActionEvent event) {
        if (Camera.getInstance() != null) {
            Camera.getInstance().setZoom(1);
            Camera.getInstance().getViewport().setX(0);
            Camera.getInstance().getTransform().setPosition(Vector2.zero());
        }
    }

    /**
     * Opens the help screen that contains the user guide
     */
    @FXML
    private void onGuide(ActionEvent event) throws IOException, URISyntaxException {
        URL url = getClass().getResource("/UserGuide/TestUserGuide.pdf");
        File file = new File(url.toURI());

        Desktop.getDesktop().open(file);
    }
}
