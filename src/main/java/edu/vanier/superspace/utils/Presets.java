package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Presets{
    EARTH("Earth", "A friendly planet!", "Terrestrial", 6380, 20, Assets.EARTH.getFilePath(), false),
    VENUS("Venus", "Beautifully lit planet!", "Terrestrial", 6052, 10, Assets.VENUS.getFilePath(), false),
    MERCURY("Mercury", "Small Planet!", "Terrestrial", 2439, 5, Assets.MERCURY.getFilePath(), false),
    MOON("Moon", "Earth's close friend", "Terrestrial", 1737, 10, Assets.MOON.getFilePath(), false),
    MARS("Mars", "Big red!", "Terrestrial", 3389, 15, Assets.MARS.getFilePath(), false),
    JUPITER("Jupiter", "Biggest planet!", "Gaseous", 69910, 10, Assets.JUPITER.getFilePath(), false),
    URANUS("Uranus", "Lol", "Ice", 25559, 10, Assets.URANUS.getFilePath(), false),
    NEPTUNE("Neptune", "darker blueee", "Ice", 24764, 10, Assets.NEPTUNE.getFilePath(), false),
    PLUTO("Pluto", "Wishes it were a planet...", "Terrestrial", 1188, 10, Assets.PLUTO.getFilePath(), false),
    CALLISTO("Callisto", "One of Jupiter's moons", "Terrestrial", 2410, 10, Assets.CALLISTO.getFilePath(), false),
    IO("Io", "One of Jupiter's moons", "Terrestrial", 1821, 10, Assets.IO.getFilePath(), false),
    EUROPA("Europa", "One of Jupiter's moons", "Terrestrial", 1561, 10, Assets.EUROPA.getFilePath(), false),
    SUN("Sun", "Huge star", "Star", 1.3927e6 / 2, 10, Assets.SUN.getFilePath(), false),
    EARTHTEST("Earth", "meow", "Terrestrial", 25, 100, Assets.EARTH.getFilePath(), false),
    MARSTEST("Mars", "Big", "Terrestrial", 25, 100, Assets.MARS.getFilePath(), false),;


    private String name;
    private String description;
    private String type;
    private double radius;
    private double mass;
    private String path;
    private boolean isPreset;

    Presets() {

    }

    Presets(String name, String description, String type, double radius, double mass, String path, boolean isPreset) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.radius = radius;
        this.mass = mass;
        this.path = path;
        this.isPreset = isPreset;
    }
}
