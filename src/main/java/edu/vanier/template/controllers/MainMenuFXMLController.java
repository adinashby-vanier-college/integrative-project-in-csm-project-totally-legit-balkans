/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        backgroundRec.setFill(new ImagePattern(new Image(getClass().getResource("/images/mainMenuBackground.png").toExternalForm())));
    }
    
    @FXML
    private void onQuit(ActionEvent event) {
        System.out.println("testing1");
    }
    
    @FXML
    private void onAddFile(ActionEvent event) {
        MainApp.switchScene(MainApp.MAINAPP_SCENE);
        System.out.println("testing2");
    }
    
}
