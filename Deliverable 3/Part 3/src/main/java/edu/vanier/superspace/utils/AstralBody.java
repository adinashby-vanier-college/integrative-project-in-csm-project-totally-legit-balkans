package edu.vanier.superspace.utils;

import edu.vanier.superspace.annotations.ToSerialize;
import lombok.Getter;
import lombok.Setter;

/**
 * Astral body is an instance that contains some properties of an entity and is a parameter for the Entity class
 */
@Getter @Setter @ToSerialize
public class AstralBody {
    private String name;
    private String description;
    private String type;
    private double radius;
    private double mass;
    private String path;
    private boolean isPreset;

    /**
     * Default constructor
     */
    public AstralBody() {}

    /**
     * Parameterized constructor
     * @param name the name of the astral body
     * @param description its description
     * @param type its type
     * @param radius its radius
     * @param mass its mass
     * @param path the file path to the image of the astral body
     * @param isPreset if it is a base preset or not
     */
    public AstralBody(String name, String description, String type, double radius, double mass, String path, boolean isPreset) {
        this();
        this.name = name;
        this.description = description;
        this.type = type;
        this.radius = radius;
        this.mass = mass;
        this.path = path;
        this.isPreset = isPreset;
    }
}
