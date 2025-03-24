package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.Main;
import edu.vanier.superspace.simulation.Simulation;
import java.io.File;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveManager {
    
    private static Logger logger = LoggerFactory.getLogger(SaveManager.class);
    
    @Getter
    private static File lastSaveFilepathParent = new File(System.getProperty("user.dir"), "/simulations/");

    @Getter
    private static File lastSaveFilepath;
    
    private static FileChooser saveFileChooser;

    @SneakyThrows
    public static void initializeFileDirctory() {
        saveFileChooser = new FileChooser();
        saveFileChooser.setInitialDirectory(lastSaveFilepathParent);
        saveFileChooser.setInitialFileName("project" + FileHelper.SIMULATION_FILE_EXTENSION);
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Simulation File", "*" + FileHelper.SIMULATION_FILE_EXTENSION));
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*")); 
    }

    public static boolean save() {
        return saveAs(lastSaveFilepath);
    }

    public static boolean saveAs() {
        saveFileChooser.setTitle("Save Simulation Project");
        File saveLocation = saveFileChooser.showSaveDialog(Application.getPrimaryStage().getOwner());

        if (saveLocation == null) {
            return false;
        }

        return saveAs(saveLocation);
    }

    private static boolean saveAs(File saveFile) {
        Simulation sim = Simulation.getInstance();
        
        if (sim == null){
            logger.error("The simulation was never created!");
            return false;
        }
        
        lastSaveFilepath = saveFile;
        lastSaveFilepathParent = saveFile.getParentFile();
        saveFileChooser.setInitialDirectory(lastSaveFilepathParent);
        
        String asJson = JsonHelper.serialize(sim);
        FileHelper.writeFileCompletely(saveFile.getAbsolutePath(), asJson);
        return true;
    }

    public static boolean load() {
        File loadPath = saveFileChooser.showOpenDialog(Application.getPrimaryStage());

        if (loadPath == null) {
            return false;
        }

        return load(loadPath);
    }

    private static boolean load(File filepath) {
        String jsonRead = FileHelper.readFileCompletely(filepath.getAbsolutePath());
        JsonHelper.deserialize(jsonRead, Simulation.class);
        
        for (var entity : Simulation.getInstance().getEntities()) {
            entity.register();
        }

        return true;
    }

    public static void clear() {
        SceneManagement.loadScene(Scenes.SIMULATION, true);
    }

    private static boolean canSaveDirectly() {
        return lastSaveFilepath != null;
    }
}
