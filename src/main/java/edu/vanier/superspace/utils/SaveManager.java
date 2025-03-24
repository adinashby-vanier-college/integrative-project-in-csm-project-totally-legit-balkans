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
    private static File lastSaveFilepathParent = new File("./simulations/");

    @Getter
    private static File lastSaveFilepath;
    
    private static FileChooser saveFileChooser;
    @SneakyThrows
    public static void initializeFileDirctory() {
        //File saveFile = new File(System.getProperty("user.dir") + "/src/main/resources/Simulation Saves/");
        saveFileChooser = new FileChooser();
        saveFileChooser.setInitialDirectory(lastSaveFilepathParent);
        saveFileChooser.setInitialFileName("project" + FileHelper.SIMULATION_FILE_EXTENSION);
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Simulation File", "*" + FileHelper.SIMULATION_FILE_EXTENSION));
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*")); 
    }

    public static void save() {
        saveAs(lastSaveFilepath);
    }

    public static void saveAs() {
        saveFileChooser.setTitle("Save Simulation Project");
        File saveLocation = saveFileChooser.showSaveDialog(Application.getPrimaryStage().getOwner());
        saveFileChooser.setInitialDirectory(saveLocation.getParentFile());
        saveAs(saveFileChooser.showOpenDialog(Application.getPrimaryStage()));
    }

    private static void saveAs(File saveFile) {
        Simulation sim = Simulation.getInstance();
        
        if (sim == null){
            logger.error("The simulation was never created!");
            return;
        }
        
        lastSaveFilepath = saveFile;
        lastSaveFilepathParent = saveFile.getParentFile();
        saveFileChooser.setInitialDirectory(lastSaveFilepathParent);
        
        String asJson = JsonHelper.serialize(sim);
        FileHelper.writeFileCompletely(saveFile.getAbsolutePath(), asJson);
        
    }

    public static void load() {
        
        File loadPath = saveFileChooser.showOpenDialog(Application.getPrimaryStage());
        load(loadPath);
        
    }

    private static void load(File filepath) {
        
        String jsonRead = FileHelper.readFileCompletely(filepath.getAbsolutePath());
        JsonHelper.deserialize(jsonRead, Simulation.class);
        
        for (var entity : Simulation.getInstance().getEntities()){
            
            entity.register();
            
        }
        
    }

    public static void clear() {
        SceneManagement.loadScene(Scenes.SIMULATION, true);
    }

    private static boolean canSaveDirectly() {
        return lastSaveFilepath != null;
    }
}
