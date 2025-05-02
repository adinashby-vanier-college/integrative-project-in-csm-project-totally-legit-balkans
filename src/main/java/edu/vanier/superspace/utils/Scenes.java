package edu.vanier.superspace.utils;

import com.sun.prism.impl.PrismSettings;
import edu.vanier.superspace.Application;
import edu.vanier.superspace.controllers.ControlBarFXMLController;
import edu.vanier.superspace.simulation.Simulation;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;

import java.util.function.Function;
import javafx.scene.shape.Rectangle;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
/**
 * Enum class for the list of scenes
 * @author mrcoc
 */
@Getter
public enum Scenes {
    LOGIN((pane,reload) -> {
        pane.setCenter(SceneManagement.loadPartial(Partials.LOGIN));
    }),
    MAIN_MENU((pane, reload) -> {
        pane.setCenter(SceneManagement.loadPartial(Partials.MAIN_MENU));
    }),
    SIMULATION((pane, reload) -> {
        AnchorPane center = new AnchorPane();
//        center.setStyle("-fx-border-color:#ff0000;");
        StackPane canvasStack;

        if (!reload) {
            canvasStack = Simulation.getInstance().getCanvasStack();
        } else {
            canvasStack  = new StackPane();
        }

        center.getChildren().add(canvasStack);
        pane.setCenter(center);
        pane.setTop(SceneManagement.loadPartial(Partials.MENU_BAR));
        pane.setBottom(SceneManagement.loadPartial(Partials.CONTROL_BAR));
        pane.setRight(SceneManagement.loadPartial(Partials.ASTRAL_CREATION));
        var resizing = new BorderPaneAutomaticResizing(pane);
        Application.getPrimaryStage().heightProperty().addListener(resizing::onResizeElement);
        Application.getPrimaryStage().widthProperty().addListener(resizing::onResizeElement);

        if (reload) {
            new Simulation(canvasStack);
        }

        center.setOnMouseClicked(Simulation.getInstance()::onSceneClicked);
    }),
    SETTINGS((pane, reload) -> pane.setCenter(SceneManagement.loadPartial(Partials.SETTINGS)));

    @FunctionalInterface
    public interface SceneInitializer {
        void initialize(BorderPane root, boolean reload);
    }

    private final SceneInitializer initializer;

    Scenes(SceneInitializer initializer) {
        this.initializer = initializer;
    }
}
