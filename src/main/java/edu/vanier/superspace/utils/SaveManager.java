package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.Main;
import java.io.File;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.SneakyThrows;

public class SaveManager {
    @Getter
    private static String lastSaveFilepathParent;

    @Getter
    private static String lastSaveFilepath;
    
    private static FileChooser saveFileChooser;
    @SneakyThrows
    public static void initializeFileDirctory() {
        File saveFile = new File(System.getProperty("user.dir") + "/src/main/resources/Simulation Saves/");
        saveFileChooser = new FileChooser();
        saveFileChooser.setInitialDirectory(saveFile);
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
