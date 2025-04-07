package edu.vanier.superspace.controllers;

import edu.vanier.superspace.utils.SaveManager;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuBarFXMLController {
    public MenuItem loadFromDiskButton;
    @FXML
    private MenuItem saveButton;
    @FXML
    private MenuItem saveAsButton;

    @FXML
    private void onEdit(ActionEvent event) {
        SceneManagement.loadScene(Scenes.MAIN_MENU);
    }

    @FXML
    private void onSave(ActionEvent event) {
        SaveManager.save();
    }

    @FXML
    private void onSaveAs(ActionEvent event) {
        boolean success = SaveManager.saveAs();
        if (success) {
            saveButton.setDisable(false);
        }
    }

    public void onLoadFromDisk(ActionEvent event) {
        SaveManager.load();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void onRequestPreferencesMenu(ActionEvent event) {
        SceneManagement.loadScene(Scenes.SETTINGS);
    }

    @FXML
    private void onDebugView(ActionEvent event) {
        // Load debug view
    }
}
