package edu.vanier.superspace.simulation;

import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.utils.BorderPaneAutomaticResizing;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Region;
import lombok.Getter;

import java.util.HashSet;

/**
 * Input class for any possible user inputs
 */
public class Input {
    private static final HashSet<Integer> JAVAFX_PRESSED_KEYS = new HashSet<>();

    private static HashSet<Integer> currentlyPressedKeys = new HashSet<>();
    private static HashSet<Integer> previouslyPressedKeys = new HashSet<>();

    @Getter
    private static double scrollDistance = 0;

    private static final int MOUSE_SCANCODES_OFFSET = 0xFFFF;

    private static Vector2 oldMouseStagePosition = Vector2.of(0, 0);

    @Getter
    private static Vector2 currentMouseScreenPosition = Vector2.of(0, 0);
    @Getter
    private static Vector2 currentMouseStagePosition = Vector2.of(0, 0);
    @Getter
    private static Vector2 currentMouseCanvasPosition = Vector2.of(0, 0);

    public static void initialize(Scene linkedScene) {
        linkedScene.addEventFilter(KeyEvent.KEY_PRESSED, Input::onKeyPressed);
        linkedScene.addEventFilter(KeyEvent.KEY_RELEASED, Input::onKeyReleased);
        linkedScene.addEventFilter(MouseEvent.MOUSE_PRESSED, Input::onMouseClicked);
        linkedScene.addEventFilter(MouseEvent.MOUSE_RELEASED, Input::onMouseReleased);
        linkedScene.addEventFilter(MouseEvent.MOUSE_MOVED, Input::onMouseMoved);
        linkedScene.addEventFilter(MouseEvent.MOUSE_DRAGGED, Input::onMouseMoved);
        linkedScene.addEventFilter(ScrollEvent.SCROLL, Input::onScroll);
    }

    /**
     * Updates the list of inputs
     */
    public static void update() {
        previouslyPressedKeys = new HashSet<>(currentlyPressedKeys);
        currentlyPressedKeys = new HashSet<>(JAVAFX_PRESSED_KEYS);
        scrollDistance = 0;
        oldMouseStagePosition = Vector2.copyOf(currentMouseStagePosition);
    }

    /**
     * Gets the rate of change of the mouse positionning on the screen.
     * @return mouse delta
     */
    public static Vector2 mouseDelta() {
        return currentMouseStagePosition.subtract(oldMouseStagePosition);
    }

    /**
     * Checks if the mouse button is pressed
     * @param button the mouse button
     * @return true or false
     */
    public static boolean isMouseButtonPressed(MouseButton button) {
        int scancode = MOUSE_SCANCODES_OFFSET + button.ordinal();
        return currentlyPressedKeys.contains(scancode) && !previouslyPressedKeys.contains(scancode);
    }

    /**
     * Checks if the mouse button is held
     * @param button the mouse button
     * @return true or false
     */
    public static boolean isMouseButtonHeld(MouseButton button) {
        int scancode = MOUSE_SCANCODES_OFFSET + button.ordinal();
        return currentlyPressedKeys.contains(scancode);
    }

    /**
     * Checks if the mouse button is released
     * @param button the mouse button
     * @return true or false
     */
    public static boolean isMouseButtonReleased(MouseButton button) {
        int scancode = MOUSE_SCANCODES_OFFSET + button.ordinal();
        return currentlyPressedKeys.contains(scancode) && !previouslyPressedKeys.contains(scancode);
    }

    /**
     * Checks if a key is pressed
     * @param code the keycode of the key
     * @return true or false
     */
    public static boolean isKeyPressed(KeyCode code) {
        int scancode = code.getCode();
        return !currentlyPressedKeys.contains(scancode) && previouslyPressedKeys.contains(scancode);
    }

    /**
     * Checks if a key is held
     * @param code the keycode of the held key
     * @return true or false
     */
    public static boolean isKeyHeld(KeyCode code) {
        int scancode = code.getCode();
        return currentlyPressedKeys.contains(scancode);
    }

    /**
     * Checks if a key is released
     * @param code the keycode of the key
     * @return true or false
     */
    public static boolean isKeyReleased(KeyCode code) {
        int scancode = code.getCode();
        return !currentlyPressedKeys.contains(scancode) && previouslyPressedKeys.contains(scancode);
    }

    /**
     * Changes the scroll distance on each scroll from the user
     * @param event the scrolling
     */
    private static void onScroll(ScrollEvent event) {
        scrollDistance += event.getDeltaY();
    }

    /**
     * Gets the position on each mouse click
     * @param event the mouse click
     */
    private static void onMouseClicked(MouseEvent event) {
        int scancode = event.getButton().ordinal();
        scancode += MOUSE_SCANCODES_OFFSET;
        JAVAFX_PRESSED_KEYS.add(scancode);
    }

    /**
     * Gets the position on each mouse release
     * @param event the mouse release event
     */
    private static void onMouseReleased(MouseEvent event) {
        int scancode = event.getButton().ordinal();
        scancode += MOUSE_SCANCODES_OFFSET;
        JAVAFX_PRESSED_KEYS.remove(scancode);
    }

    /**
     * Gets the position on each movement of the mouse
     * @param event the movement of the mouse
     */
    private static void onMouseMoved(MouseEvent event) {
        currentMouseStagePosition = Vector2.of(event.getX(), event.getY());
        currentMouseScreenPosition = Vector2.of(event.getScreenX(), event.getScreenY());
        if (BorderPaneAutomaticResizing.getInstance() != null) {
            currentMouseCanvasPosition = currentMouseStagePosition.subtract(Vector2.of(0, ((Region) BorderPaneAutomaticResizing.getInstance().getPane().getTop()).getHeight()));
        }
    }

    /**
     * Adds to the HashSet the code of the key that is pressed
     * @param event key pressed
     */
    private static void onKeyPressed(KeyEvent event) {
        int scancode = event.getCode().getCode();
        JAVAFX_PRESSED_KEYS.add(scancode);
    }

    /**
     * Adds to the HashSet the code of the key that is released
     * @param event key released
     */
    private static void onKeyReleased(KeyEvent event) {
        int scancode = event.getCode().getCode();
        JAVAFX_PRESSED_KEYS.remove(scancode);
    }
}
