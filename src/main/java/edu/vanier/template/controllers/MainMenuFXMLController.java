/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.controllers;

//import edu.vanier.template.ui.MainApp;
import edu.vanier.superspace.Main;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * https://freepngimg.com/png/19262-space-png-clipart
 * @author mrcoc
 */
public class MainMenuFXMLController {
    
    @FXML
    private Rectangle backgroundRec;
    @FXML
    private Button quitButton;
    @FXML
    private Button addFileButton;
    @FXML
    private HBox fileHBox;
    
    @FXML
    public void initialize(){
        backgroundRec.setFill(new ImagePattern(new Image(Main.class.getResource("/images/mainMenuBackground.png").toExternalForm())));
    }
    
    @FXML
    private void onQuit(ActionEvent event) {
        System.out.println("testing1");
    }
    
    @FXML
    private void onAddFile(ActionEvent event) {
        SceneManagement.loadScene(Scenes.MAIN_APP);
        System.out.println("testing2");
        //newSelectorButton(new File test);
    }
    
      private Button newSelectorButton(File fileToLoad) {
        Button newSelectorButton = new Button(fileToLoad.getName());

        try {
            int endIndexOfRawName = fileToLoad.getAbsolutePath().length();// - FileHelper.SIMULATION_FILE_EXTENSION.length();
            String iconFileName = fileToLoad.getAbsolutePath().substring(0, endIndexOfRawName);// + FileHelper.SIMULATION_ICON_EXTENSION;
            FileInputStream iconInputStream = new FileInputStream(iconFileName);
            ImageView imageView = new ImageView(new Image(iconInputStream));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(100);
            imageView.setFitWidth(150);

            newSelectorButton.setGraphic(imageView);
        } catch (FileNotFoundException ex) {
            System.out.println("Icon for scene '" + fileToLoad.getName() + "' does not exist!");
        }

        newSelectorButton.setOnAction(e -> {
            //SceneManager.loadSimulationScene(fileToLoad);
            //hide();
        });

        newSelectorButton.setContentDisplay(ContentDisplay.TOP);
        return newSelectorButton;
    }
}
