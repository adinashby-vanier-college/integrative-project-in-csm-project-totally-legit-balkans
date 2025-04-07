package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Presets{
    EARTH("Earth", "A friendly planet!", "Terrestrial", 25, 10, Assets.EARTH.getFilePath(), false),
    VENUS("Venus", "Beautifully lit planet!", "Terrestrial", 25, 10, Assets.VENUS.getFilePath(), false),
    MERCURY("Mercury", "Small Planet!", "Terrestrial", 18, 8, Assets.MERCURY.getFilePath(), false),
    MOON("Moon", "Earth's close friend", "Terrestrial", 15, 8, Assets.MOON.getFilePath(), false),
    MARS("Mars", "Big red!", "Terrestrial", 20, 15, Assets.MARS.getFilePath(), false),
    JUPITER("Jupiter", "Biggest planet!", "Gaseous", 35, 20, Assets.JUPITER.getFilePath(), false),
    URANUS("Uranus", "Lol", "Ice", 30, 15, Assets.URANUS.getFilePath(), false),
    NEPTUNE("Neptune", "darker blueee", "Ice", 30, 15, Assets.NEPTUNE.getFilePath(), false),
    PLUTO("Pluto", "Wishes it were a planet...", "Terrestrial", 13, 8, Assets.PLUTO.getFilePath(), false),
    CALLISTO("Callisto", "One of Jupiter's moons", "Terrestrial", 18, 8, Assets.CALLISTO.getFilePath(), false),
    IO("Io", "One of Jupiter's moons", "Terrestrial", 15, 8, Assets.IO.getFilePath(), false),
    EUROPA("Europa", "One of Jupiter's moons", "Terrestrial", 15, 10, Assets.EUROPA.getFilePath(), false),
    SUN("Sun", "Huge star", "Star", 50, 40, Assets.SUN.getFilePath(), false);
//    EARTHTEST("Earth", "meow", "Terrestrial", 25, 8, Assets.EARTH.getFilePath(), false),
//    MARSTEST("Mars", "Big", "Terrestrial", 25, 8, Assets.MARS.getFilePath(), false);


    private String name;
    private String description;
    private String type;
    private double radius;
    private double mass;
    private String path;
    private boolean isPreset;

    Presets() {}

    Presets(String name, String description, String type, double radius, double mass, String path, boolean isPreset) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.radius = radius;
        this.mass = mass;
        this.path = path;
        this.isPreset = isPreset;
    }

    public double getActualRadius(Presets preset) {
        double actualRadius = 0;

        switch (preset) {
            case EARTH -> actualRadius = 6380;
            case VENUS -> actualRadius = 6052;
            case MERCURY -> actualRadius = 2439;
            case IO -> actualRadius = 1821;
            case SUN -> actualRadius = 1.3927e6 / 2;
            case MARS -> actualRadius = 3389;
            case MOON -> actualRadius = 1737;
            case PLUTO -> actualRadius = 1188;
            case EUROPA -> actualRadius = 1561;
            case URANUS -> actualRadius = 25559;
            case JUPITER -> actualRadius = 69910;
            case NEPTUNE -> actualRadius = 24764;
            case CALLISTO -> actualRadius = 2410;
        }

        return actualRadius;
    }
}
