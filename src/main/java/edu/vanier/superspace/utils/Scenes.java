package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Scenes {
    MAIN_MENU("");

    private final String filepath;

    Scenes(String filepath) {
        this.filepath = "/fxml/" + filepath + ".fxml";
    }
}
