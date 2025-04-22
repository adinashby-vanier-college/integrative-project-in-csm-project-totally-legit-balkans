package edu.vanier.superspace.controllers;

import edu.vanier.superspace.utils.Account;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogInFXMLController{
    
    private static LogInFXMLController instance;

    @FXML
    private Button logBtn;

    @FXML
    private Label messageTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button signBtn;

    @FXML
    private TextField usernameTxt;
    
     @FXML
    void logClick(ActionEvent event) {
        
        for(int i = 0; i < Account.accounts.size(); i++){
            

            if(Account.accounts.get(i).getUsername().equalsIgnoreCase(usernameTxt.getText())) {
                if(Account.accounts.get(i).getPassword().equalsIgnoreCase(passwordTxt.getText())){
                    SceneManagement.loadScene(Scenes.MAIN_MENU);
                }
            }
              
        }
        
    }

    @FXML
    void signClick(ActionEvent event) {
        
        Account.accounts.add(new Account(usernameTxt.getText(),passwordTxt.getText()));
        Account.save();
        
        
    }

}
