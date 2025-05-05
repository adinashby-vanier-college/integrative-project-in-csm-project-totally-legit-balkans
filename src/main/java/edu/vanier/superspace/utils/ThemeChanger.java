/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

import javafx.scene.Scene;

/**
 * Class is a helper for changing the theme
 */
public class ThemeChanger {
    /**
     * Data field
     */
    public static boolean light = true;

    /**
     * Changes the theme in a given scene
     * @param scene the scene to change the theme in
     */
    public void changeTheme(Scene scene){
        scene.getStylesheets().clear();
        
        if(light){
            scene.getStylesheets().add(getClass().getResource("/css/LightTheme.css").toExternalForm());
        }else{
            scene.getStylesheets().add(getClass().getResource("/css/DarkTheme.css").toExternalForm());
        }
    }
}
