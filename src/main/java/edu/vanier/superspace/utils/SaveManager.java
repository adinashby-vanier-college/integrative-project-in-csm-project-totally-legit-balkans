package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.Main;
import edu.vanier.superspace.simulation.Simulation;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;


public class SaveManager {
    @Getter
    private static String lastSaveFilepathParent;

    @Getter @Setter
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
        saveAs(saveLocation);
    }

    private static void saveAs(File saveFile) {
        if (saveFile != null) {
            try {
                SceneManagement.exportSimulationScene(saveFile);
                generateAndWriteSaveIcon(saveFile);
                Simulation.getInstance().setSaveLocation(saveFile);
                setLastSaveFilepath(saveFile.getAbsolutePath());
                Application.getPrimaryStage().setTitle("Balls");
            } catch (Exception ex) {
                System.out.println("Export Error");
            }
        }
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
//        WritableImage sceneIcon = Simulation.getInstance().getIconScreenshot();
//        ImageIO.write(SwingFXUtils.fromFXImage(sceneIcon, null), "png", iconWriteFile);
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
