package edu.vanier.superspace.utils;

import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class BorderPaneAutomaticResizing {
    private boolean top = false;
    private boolean bottom = false;
    private boolean right = false;
    private boolean left = false;

    private final BorderPane pane;

    public BorderPaneAutomaticResizing(BorderPane pane, boolean top, boolean bottom, boolean right, boolean left) {
        this.pane = pane;

        if (top) {
            ((TitledPane)pane.getTop()).widthProperty().addListener(this::onResizeElement);
            ((TitledPane)pane.getTop()).heightProperty().addListener(this::onResizeElement);
            this.top = true;
        }

        if (bottom) {
            ((TitledPane)pane.getBottom()).widthProperty().addListener(this::onResizeElement);
            ((TitledPane)pane.getBottom()).heightProperty().addListener(this::onResizeElement);
            this.bottom = true;
        }

        if (right) {
            ((TitledPane)pane.getRight()).widthProperty().addListener(this::onResizeElement);
            ((TitledPane)pane.getRight()).heightProperty().addListener(this::onResizeElement);
            this.right = true;
        }

        if (left) {
            ((TitledPane)pane.getLeft()).widthProperty().addListener(this::onResizeElement);
            ((TitledPane)pane.getLeft()).heightProperty().addListener(this::onResizeElement);
            this.left = true;
        }
    }

    private void onResizeElement(Observable obs, Number oldValue, Number newValue) {
        double height = pane.getHeight();
        double width = pane.getWidth();

        if (top) {
            height -= ((TitledPane)pane.getTop()).getHeight();
        }

        if (bottom) {
            height -= ((TitledPane)pane.getBottom()).getHeight();
        }

        if (right) {
            width -= ((TitledPane)pane.getRight()).getWidth();
        }

        if (left) {
            width -= ((TitledPane)pane.getTop()).getWidth();
        }

        ((AnchorPane)pane.getCenter()).setPrefHeight(height);
        ((AnchorPane)pane.getCenter()).setPrefHeight(width);
    }
}
