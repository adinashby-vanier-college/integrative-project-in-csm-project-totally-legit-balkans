package edu.vanier.superspace.utils;

import com.sun.prism.impl.PrismSettings;
import edu.vanier.superspace.simulation.Simulation;
import javafx.scene.layout.BorderPane;
import lombok.Getter;

import java.util.function.Function;

@Getter
public enum Scenes {
    MAIN_MENU((pane, reload) -> {
        pane.setCenter(SceneManagement.loadPartial(Partials.MAIN_MENU));
    }),
    SIMULATION((pane, reload) -> {
        if (reload) {
            new Simulation();
        }
//        pane.setCenter(SceneManagement.loadPartial(Partials.MAIN_APP));
        pane.setTop(SceneManagement.loadPartial(Partials.MENU_BAR));
        pane.setBottom(SceneManagement.loadPartial(Partials.CONTROL_BAR));
        pane.setRight(SceneManagement.loadPartial(Partials.ASTRAL_CREATION));
    }),
    SETTINGS((pane, reload) -> {
        pane.setCenter(SceneManagement.loadPartial(Partials.SETTINGS));
    });

    @FunctionalInterface
    public interface SceneInitializer {
        void initialize(BorderPane root, boolean reload);
    }

    private final SceneInitializer initializer;

    Scenes(SceneInitializer initializer) {
        this.initializer = initializer;
    }
}
