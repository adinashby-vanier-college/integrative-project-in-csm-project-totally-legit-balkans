package edu.vanier.superspace.utils;

import lombok.Getter;

/**
 * Enum containing the different partial scenes
 */
@Getter
public enum Partials {
    LOGIN("logInPage"),
    MAIN_MENU("mainMenu"),
    ASTRAL_CREATION("mainApp_astralCreation"),
    CONTROL_BAR("mainApp_controlBar"),
    MENU_BAR("mainApp_menuBar"),
    SETTINGS("settingsMenu");

    private final String filepath;

    Partials(String filepath) {
        this.filepath = "/fxml/" + filepath + ".fxml";
    }
}
