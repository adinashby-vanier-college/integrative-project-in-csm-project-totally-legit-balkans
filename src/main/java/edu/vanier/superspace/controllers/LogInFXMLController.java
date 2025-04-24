package edu.vanier.superspace.controllers;

import edu.vanier.superspace.utils.Account;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LogInFXMLController{
    
    private static LogInFXMLController instance;

    @FXML
    private Button logBtn;

    @FXML
    private Label messageTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button signBtn;

    @FXML
    private TextField usernameTxt;
    
     @FXML
    void logClick(ActionEvent event) {
        
        if(Account.matchAccount(usernameTxt.getText(), passwordTxt.getText())){
            SceneManagement.loadScene(Scenes.MAIN_MENU);
        }else{
            messageTxt.setTextFill(Color.RED);
            messageTxt.setText("The username or password are incorrect!");  
        }
        
        
    }

    @FXML
    void signClick(ActionEvent event) {
        
        if(!Account.sameUsername(usernameTxt.getText())){
            Account.accounts.add(new Account(usernameTxt.getText(),passwordTxt.getText()));
            Account.save();
            messageTxt.setTextFill(Color.LIGHTGREEN);
            messageTxt.setText(usernameTxt.getText()+" account has been created!");
        }else{
            messageTxt.setTextFill(Color.RED);
            messageTxt.setText("The username has already been taken!"); 
        }
        
        
    }

}
