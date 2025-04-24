package edu.vanier.superspace.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * The dimensions of the application that should be rendered
 */
public class RenderDimensions {
    @Getter @Setter private static double applicationTargetWidth = 1280;
    @Getter @Setter private static double applicationTargetHeight = 720;
    @Getter @Setter private static double applicationMinWidth = 1200;
    @Getter @Setter private static double applicationMinHeight = 700;
}
