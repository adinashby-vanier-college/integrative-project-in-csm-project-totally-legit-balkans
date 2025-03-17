/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 2361272
 */
public class SimulationSettings {
    
    private static final String SIMULATION_SETTINGS_FILEPATH = "Settings Files/settings.json";
    
    @Getter
    private static SimulationSettings instance;
    
    @Getter @Setter
    private Font globalFont;
    @Getter @Setter
    private Image menuBackground;
    @Getter @Setter
    private Image simulationBackground;
    
    
    public static void initialize() {
        SimulationSettings.instance = new SimulationSettings();
    }
}
