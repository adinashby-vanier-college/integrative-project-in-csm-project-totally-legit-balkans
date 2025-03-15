package edu.vanier.superspace.utils;

import lombok.Getter;

public class SaveManager {
    @Getter
    private static String lastSaveFilepathParent;

    @Getter
    private static String lastSaveFilepath;

    private static void save() {

    }

    private static void saveAs(String filepath) {

    }

    private static void load(String filepath) {

    }

    private static void clear() {

    }

    private static boolean canSaveDirectly() {
        return lastSaveFilepath != null && !lastSaveFilepath.isEmpty();
    }
}
