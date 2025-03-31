package edu.vanier.superspace.controllers;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.utils.FileHelper;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import edu.vanier.superspace.utils.SimulationSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class for the main menu scene.
 */
public class MainMenuFXMLController {
    @Getter
    private static MainMenuFXMLController instance;
    
    private static final File DIR_TO_SCAN = new File(System.getProperty("user.dir") + "/src/main/resources/Simulation Saves/");
    
    private final static Logger logger = LoggerFactory.getLogger(MainMenuFXMLController.class);
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView imgViewBackground;
    @FXML
    private HBox hboxSceneSelector;
    
    @FXML
    public void initialize() {
        instance = this;
        imgViewBackground.setImage(SimulationSettings.getInstance().getMenuBackground());
        imgViewBackground.fitHeightProperty().bind(Application.getPrimaryStage().heightProperty());
        imgViewBackground.fitWidthProperty().bind(Application.getPrimaryStage().widthProperty());
        logger.info("Initializing MainMenuFXMLController...");
        repopulateSimulationFiles();
    }

    @FXML
    private void onQuitButtonClicked() {
        //TODO: Add some animation to the button on hover
        Stage currentStage = (Stage) btnQuit.getScene().getWindow();

        logger.info("Closing the application...");
        currentStage.close();
    }

    @FXML
    private void onAddButtonClicked() {
        logger.info("Adding a simulation...");

        SceneManagement.loadScene(Scenes.SIMULATION, true);
    }
    
    private void repopulateSimulationFiles() {
        File[] simulationFiles = DIR_TO_SCAN.listFiles(e -> e.isFile() && e.getName().contains(FileHelper.SIMULATION_FILE_EXTENSION));
        if (simulationFiles == null || simulationFiles.length == 0) {
            hboxSceneSelector.getChildren().add(new Label("No preset simulations found."));
        } else {
            for (File simulationFile : simulationFiles) {
                        
                hboxSceneSelector.getChildren().addFirst(createNewFileButton(simulationFile));
            }
        }
    }
    
     private Button createNewFileButton(File loadFile) {
        Button newSelectorButton = new Button(loadFile.getName());

        try {
            int endIndexOfRawName = loadFile.getAbsolutePath().length() - FileHelper.SIMULATION_FILE_EXTENSION.length();
            String iconFileName = loadFile.getAbsolutePath().substring(0, endIndexOfRawName) + FileHelper.SIMULATION_ICON_EXTENSION;
            FileInputStream iconInputStream = new FileInputStream(iconFileName);
            ImageView imageView = new ImageView(new Image(iconInputStream));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(100);
            imageView.setFitWidth(150);
            newSelectorButton.setGraphic(imageView);
        } catch (FileNotFoundException ex) {
            System.out.println("Icon for scene '" + loadFile.getName() + "' does not exist!");
            java.util.logging.Logger.getLogger(MainMenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        newSelectorButton.setOnAction(e -> {
//            SceneManagement.loadScene(loadFile);
//            hide();
        });

        newSelectorButton.setContentDisplay(ContentDisplay.TOP);
        return newSelectorButton;
    }
    
}
