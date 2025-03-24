/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javax.swing.text.StyledEditorKit;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

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
    private Image menuBackground = new Image("fxml/Images/MainMenuBackground.png");
    @Getter @Setter
    private Image simulationBackground;
    @Getter @Setter
    private boolean lightTheme;
    
    
    public static void initialize() {
        SimulationSettings.instance = new SimulationSettings();
    }
    
    @SneakyThrows
    public static void saveToFile(){
        String json = JsonHelper.toJson(instance);
        File writeFile = new File(SIMULATION_SETTINGS_FILEPATH);
        writeFile.getParentFile().mkdirs();
        System.out.println(writeFile.getAbsolutePath());
        if (!writeFile.exists()) {
            writeFile.createNewFile();
        }
        FileHelper.writeFileCompletely(writeFile.getAbsolutePath(), json);
    }
    
    @SneakyThrows
    public static void loadFromFile(){
        File saveFile = new File(SIMULATION_SETTINGS_FILEPATH);
        if(!saveFile.exists()) {
            instance = new SimulationSettings();
            System.out.println("making new file");
            return;
        }
//        instance = JsonHelper.fromJson(FileHelper.readFileCompletely(SIMULATION_SETTINGS_FILEPATH), SimulationSettings.class);
    }
    
    
}
