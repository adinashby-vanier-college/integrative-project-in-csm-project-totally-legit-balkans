package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.simulation.Simulation;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
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
    public static void initializeFileDirectory() {
        File saveFile = new File(System.getProperty("user.dir") + "/src/main/resources/Simulation Saves/");
        saveFileChooser = new FileChooser();
        saveFileChooser.setInitialDirectory(saveFile);
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
        generateAndWriteSaveIcon(saveFile);
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

    public static boolean load(File filepath) {
        String jsonRead = FileHelper.readFileCompletely(filepath.getAbsolutePath());
        JsonHelper.deserialize(jsonRead, Simulation.class);
        
        for (var entity : Simulation.getInstance().getEntities()) {
            entity.registerFromLoadedFile();
        }

        return true;
    }

    @SneakyThrows
    private static void generateAndWriteSaveIcon(File jsonWritePath) {
        String iconWritePath = jsonWritePath.getAbsolutePath().substring(0, jsonWritePath.getAbsolutePath().length() - 4);
        File iconWriteFile = new File(iconWritePath + FileHelper.SIMULATION_ICON_EXTENSION);
        Image img = new Image("fxml/Images/MainMenuBackground.png");
        BufferedImage bff = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gr = bff.createGraphics();
        gr.drawImage(bff, 100, 150, null);
        gr.dispose();
        ImageIO.write(bff, "png", iconWriteFile);
        WritableImage sceneIcon = Simulation.getInstance().getIconScreenshot();
        ImageIO.write(SwingFXUtils.fromFXImage(sceneIcon, null), "png", iconWriteFile);
    }
    
    public static void clear() {
        SceneManagement.loadScene(Scenes.SIMULATION, true);
    }

    private static boolean canSaveDirectly() {
        return lastSaveFilepath != null;
    }
}
