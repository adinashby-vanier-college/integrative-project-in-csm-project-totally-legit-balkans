package edu.vanier.superspace;

import edu.vanier.superspace.utils.RenderDimensions;
import edu.vanier.superspace.utils.SceneManagement;
import edu.vanier.superspace.utils.SaveManager;
import edu.vanier.superspace.utils.Scenes;
import edu.vanier.superspace.utils.SimulationSettings;
import javafx.stage.Stage;
import lombok.Getter;

public class Application extends javafx.application.Application {
    @Getter
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        SimulationSettings.initialize();
        SaveManager.initializeFileDirectory();
        Application.primaryStage = primaryStage;

        SceneManagement.loadScene(Scenes.MAIN_MENU);

        primaryStage.setTitle("Super Space Maker");
        primaryStage.setMinHeight(RenderDimensions.getApplicationMinHeight());
        primaryStage.setMinWidth(RenderDimensions.getApplicationMinWidth());

        // Put this application's main window on top of other already-opened windows upon launching the app
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(false);
    }

    @Override
    public void stop() {

    }
}
