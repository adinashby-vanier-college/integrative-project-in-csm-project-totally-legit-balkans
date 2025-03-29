package edu.vanier.superspace.simulation;

import edu.vanier.superspace.mathematics.Vector2;
import javafx.scene.Scene;
import javafx.scene.input.*;
import lombok.Getter;

import java.util.HashSet;

public class Input {
    private static final HashSet<Integer> JAVAFX_PRESSED_KEYS = new HashSet<>();

    private static HashSet<Integer> currentlyPressedKeys = new HashSet<>();
    private static HashSet<Integer> previouslyPressedKeys = new HashSet<>();

    @Getter
    private static double scrollDistance = 0;

    private static final int MOUSE_SCANCODES_OFFSET = 0xFFFF;

    private static Vector2 oldMouseScreenPosition = Vector2.of(0, 0);

    @Getter
    private static Vector2 currentMouseScreenPosition = Vector2.of(0, 0);

    public static void initialize(Scene linkedScene) {
        linkedScene.addEventFilter(KeyEvent.KEY_PRESSED, Input::onKeyPressed);
        linkedScene.addEventFilter(KeyEvent.KEY_RELEASED, Input::onKeyReleased);
        linkedScene.addEventFilter(MouseEvent.MOUSE_PRESSED, Input::onMouseClicked);
        linkedScene.addEventFilter(MouseEvent.MOUSE_RELEASED, Input::onMouseReleased);
        linkedScene.addEventFilter(MouseEvent.MOUSE_MOVED, Input::onMouseMoved);
        linkedScene.addEventFilter(MouseEvent.MOUSE_DRAGGED, Input::onMouseMoved);
        linkedScene.addEventFilter(ScrollEvent.SCROLL, Input::onScroll);
    }

    public static void update() {
        previouslyPressedKeys = new HashSet<>(currentlyPressedKeys);
        currentlyPressedKeys = new HashSet<>(JAVAFX_PRESSED_KEYS);
        scrollDistance = 0;
        oldMouseScreenPosition = Vector2.copyOf(currentMouseScreenPosition);
    }

    public static Vector2 mouseDelta() {
        return currentMouseScreenPosition.subtract(oldMouseScreenPosition);
    }

    public static boolean isMouseButtonPressed(MouseButton button) {
        int scancode = MOUSE_SCANCODES_OFFSET + button.ordinal();
        return currentlyPressedKeys.contains(scancode) && !previouslyPressedKeys.contains(scancode);
    }

    public static boolean isMouseButtonHeld(MouseButton button) {
        int scancode = MOUSE_SCANCODES_OFFSET + button.ordinal();
        return currentlyPressedKeys.contains(scancode);
    }

    public static boolean isMouseButtonReleased(MouseButton button) {
        int scancode = MOUSE_SCANCODES_OFFSET + button.ordinal();
        return currentlyPressedKeys.contains(scancode) && !previouslyPressedKeys.contains(scancode);
    }

    public static boolean isKeyPressed(KeyCode code) {
        int scancode = code.getCode();
        return !currentlyPressedKeys.contains(scancode) && previouslyPressedKeys.contains(scancode);
    }

    public static boolean isKeyHeld(KeyCode code) {
        int scancode = code.getCode();
        return currentlyPressedKeys.contains(scancode);
    }

    public static boolean isKeyReleased(KeyCode code) {
        int scancode = code.getCode();
        return !currentlyPressedKeys.contains(scancode) && previouslyPressedKeys.contains(scancode);
    }

    private static void onScroll(ScrollEvent event) {
        scrollDistance += event.getDeltaY();
    }

    private static void onMouseClicked(MouseEvent event) {
        int scancode = event.getButton().ordinal();
        scancode += MOUSE_SCANCODES_OFFSET;
        JAVAFX_PRESSED_KEYS.add(scancode);
    }

    private static void onMouseReleased(MouseEvent event) {
        int scancode = event.getButton().ordinal();
        scancode += MOUSE_SCANCODES_OFFSET;
        JAVAFX_PRESSED_KEYS.remove(scancode);
    }

    private static void onMouseMoved(MouseEvent event) {
        currentMouseScreenPosition = Vector2.of(event.getX(), event.getY());
    }

    private static void onKeyPressed(KeyEvent event) {
        int scancode = event.getCode().getCode();
        JAVAFX_PRESSED_KEYS.add(scancode);
    }

    private static void onKeyReleased(KeyEvent event) {
        int scancode = event.getCode().getCode();
        JAVAFX_PRESSED_KEYS.remove(scancode);
    }
}
