package edu.vanier.superspace.utils;

import edu.vanier.superspace.Application;
import edu.vanier.superspace.mathematics.Vector2;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Vector;
import lombok.SneakyThrows;

public class BorderPaneAutomaticResizing {
    @Getter
    private static BorderPaneAutomaticResizing instance;

    @Getter
    private final BorderPane pane;

    public BorderPaneAutomaticResizing(BorderPane pane) {
        instance = this;
        this.pane = pane;

        if (pane.getTop() != null && pane.getTop() instanceof TitledPane titledPane) {
            titledPane.widthProperty().addListener(this::onResizeElement);
            titledPane.heightProperty().addListener(this::onResizeElement);
        }

        if (pane.getBottom() != null && pane.getBottom() instanceof TitledPane titledPane) {
            titledPane.widthProperty().addListener(this::onResizeElement);
            titledPane.heightProperty().addListener(this::onResizeElement);
        }

        if (pane.getRight() != null && pane.getRight() instanceof TitledPane titledPane) {
            titledPane.widthProperty().addListener(this::onResizeElement);
            titledPane.heightProperty().addListener(this::onResizeElement);
        }

        if (pane.getLeft() != null && pane.getLeft() instanceof TitledPane titledPane) {
            titledPane.widthProperty().addListener(this::onResizeElement);
            titledPane.heightProperty().addListener(this::onResizeElement);
        }
    }

    public Vector2 topLeftCornerPositionOffset() {
        double y = Application.getPrimaryStage().getHeight();
        y -= pane.getHeight();
        return Vector2.of(0, y);
    }

    public void onResizeElement(Observable obs, Number oldValue, Number newValue) {
        double height = pane.getHeight();
        double width = pane.getWidth();

        if (pane.getTop() != null) {
            height -= ((Region)pane.getTop()).getHeight();
        }

        if (pane.getBottom() != null) {
            height -= ((Region)pane.getBottom()).getHeight();
        }

        if (pane.getRight() != null) {
            width -= ((Region)pane.getRight()).getWidth();
        }

        if (pane.getLeft() != null) {
            width -= ((Region)pane.getTop()).getWidth();
        }

        var anchorPane = (AnchorPane)pane.getCenter();
        anchorPane.setPrefHeight(height);
        anchorPane.setPrefWidth(width);

        double finalHeight = height;
        double finalWidth = width;
 
        //Java moment
        try{
            ((StackPane)anchorPane.getChildren().getFirst()).getChildren().forEach(c -> ((Canvas)c).setHeight(finalHeight));
            ((StackPane)anchorPane.getChildren().getFirst()).getChildren().forEach(c -> ((Canvas)c).setWidth(finalWidth));
        }catch(Exception e){
            
        }
        
    }
}
