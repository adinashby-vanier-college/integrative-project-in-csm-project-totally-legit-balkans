package edu.vanier.superspace.simulation;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Input {
    private ArrayList<Integer> currentlyPressedKeys = new ArrayList<>();
    private ArrayList<Integer> previouslyPressedKeys = new ArrayList<>();

    public void update() {

    }

    public boolean isKeyPressed(KeyCode code) {
        return false;
    }

    public boolean isKeyHeld(KeyCode code) {
        return false;
    }

    public boolean isKeyReleased(KeyCode code) {
        return false;
    }

    private void onMouseClicked(MouseEvent event) {

    }

    private void onMouseReleased(MouseEvent event) {

    }

    private void onMouseMoved(MouseEvent event) {

    }

    private void onKeyPressed(KeyEvent event) {

    }

    private void onKeyReleased(KeyEvent event) {

    }
}
