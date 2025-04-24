package edu.vanier.superspace.utils;

import javafx.scene.image.Image;
import lombok.Getter;

/**
 * Helper with the image loading
 */
public class ImageLoader {
    @Getter
    private static final String defaultPath = "/Sprites/DefaultImage.png";

    /**
     * Tries to load an image and returns false if it catches an exception
     * @param path the path of the image
     * @return true or false
     */
    public static boolean testLoad(String path) {
        try {
            Image image = new Image(path);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
