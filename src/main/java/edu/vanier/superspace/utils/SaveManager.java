package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import java.io.File;
import javafx.stage.FileChooser;
import lombok.Getter;

public class SaveManager {
    @Getter
    private static String lastSaveFilepathParent;

    @Getter
    private static String lastSaveFilepath;
    
    private static FileChooser saveFileChooser;
    
    public static void initializeFileDirctory() {
        saveFileChooser = new FileChooser();
        saveFileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/Space Simulation Files/"));
        saveFileChooser.setInitialFileName("project" + FileHelper.SIMULATION_FILE_EXTENSION);
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Simulation File", "*" + FileHelper.SIMULATION_FILE_EXTENSION));
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*")); 
    }

    public static void save() {

    }

    public static void saveAs() {
        saveFileChooser.setTitle("Save Simulation Project");
        File saveLocation = saveFileChooser.showSaveDialog(Application.getPrimaryStage().getOwner());
        saveFileChooser.setInitialDirectory(saveLocation.getParentFile());
        saveAs(saveFileChooser.showOpenDialog(Application.getPrimaryStage()));
    }

    private static void saveAs(File saveFile) {

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
