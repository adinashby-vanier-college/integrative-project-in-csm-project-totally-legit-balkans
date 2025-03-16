package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Scenes {
    MAIN_MENU("mainMenu"),
    MAIN_APP("mainApp"),
    ASTRAL_CREATION("mainApp_astralCreation"),
    CONTROL_BAR("mainApp_controlBar"),
    MENU_BAR("mainApp_menuBar"),
    SETTINGS("settingsMenu");

    private final String filepath;

    Scenes(String filepath) {
        this.filepath = "/fxml/" + filepath + ".fxml";
    }
}
