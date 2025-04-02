package edu.vanier.superspace.utils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AstralBody {
    private String name;
    private String description;
    private String type;
    private double radius;
    private double mass;
    private String path;
    private boolean isPreset;

    public AstralBody(String name, String description, String type, double radius, double mass, String path, boolean isPreset) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.radius = radius;
        this.mass = mass;
        this.path = path;
        this.isPreset = isPreset;
    }
}
