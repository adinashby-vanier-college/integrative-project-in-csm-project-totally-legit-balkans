package edu.vanier.superspace.utils;

import lombok.Getter;

public class SaveManager {
    @Getter
    private static String lastSaveFilepathParent;

    @Getter
    private static String lastSaveFilepath;

    public static void save() {

    }

    public static void saveAs() {

    }

    private static void saveAs(String filepath) {

    }

    public static void load() {

    }

    private static void load(String filepath) {

    }

    public static void clear() {

    }

    private static boolean canSaveDirectly() {
        return lastSaveFilepath != null && !lastSaveFilepath.isEmpty();
    }
}
