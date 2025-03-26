package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Presets {
    EARTH("Earth", "A friendly planet!", "Terrestrial", 20, 20, getEarthPath()),
    VENUS("Venus", "Beautifully lit planet!", "Terrestrial", 10, 10, getVenusPath()),
    MERCURY("Mercury", "Small Planet!", "Terrestrial", 5, 5, getMercuryPath()),
    MOON("Moon", "Earth's close friend", "Terrestrial", 10, 10, getMoonPath()),
    MARS("Mars", "Big red!", "Terrestrial", 15, 15, getMarsPath()),
    JUPITER("Jupiter", "Biggest planet!", "Gaseous", 10, 10, getJupiterPath()),
    URANUS("Uranus", "Lol", "Ice", 10, 10, getUranusPath()),
    NEPTUNE("Neptune", "darker blueee", "Ice", 10, 10, getNeptunePath()),
    PLUTO("Pluto", "Wishes it were a planet...", "Terrestrial", 10, 10, getPlutoPath()),
    CALLISTO("Callisto", "One of Jupiter's moons", "Terrestrial", 10, 10, getCallistoPath()),
    IO("Io", "One of Jupiter's moons", "Terrestrial", 10, 10, getIOPath()),
    EUROPA("Europa", "One of Jupiter's moons", "Terrestrial", 10, 10, getEuropaPath()),
    SUN("Sun", "Huge star", "Star", 10, 10, getSunPath()),
    SATURN();


    private String name;
    private String description;
    private String type;
    private double radius;
    private double mass;
    private String path;

    Presets() {

    }

    Presets(String name, String description, String type, double radius, double mass, String path) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.radius = radius;
        this.mass = mass;
        this.path = path;
    }

    public static String getEarthPath() {
        return Presets.class.getResource("/Sprites/Planets/Earth.png").toExternalForm();
    }

    public static String getVenusPath() {
        return Presets.class.getResource("/Sprites/Planets/Venus.png").toExternalForm();
    }

    public static String getMercuryPath() {
        return Presets.class.getResource("/Sprites/Planets/Mercury.png").toExternalForm();
    }

    public static String getMarsPath() {
        return Presets.class.getResource("/Sprites/Planets/Mars.png").toExternalForm();
    }

    public static String getJupiterPath() {
        return Presets.class.getResource("/Sprites/Planets/Jupiter.png").toExternalForm();
    }

    public static String getUranusPath() {
        return Presets.class.getResource("/Sprites/Planets/Uranus.png").toExternalForm();
    }

    public static String getNeptunePath() {
        return Presets.class.getResource("/Sprites/Planets/Neptune.png").toExternalForm();
    }

    public static String getPlutoPath() {
        return Presets.class.getResource("/Sprites/Planets/Pluto.png").toExternalForm();
    }

    public static String getCallistoPath() {
        return Presets.class.getResource("/Sprites/Planets/Callisto.png").toExternalForm();
    }

    public static String getIOPath() {
        return Presets.class.getResource("/Sprites/Planets/Io.png").toExternalForm();
    }

    public static String getEuropaPath() {
        return Presets.class.getResource("/Sprites/Planets/Europa.png").toExternalForm();
    }

    public static String getSunPath() {
        return Presets.class.getResource("/Sprites/Planets/Sun.png").toExternalForm();
    }

    public static String getMoonPath() {
        return Presets.class.getResource("/Sprites/Planets/TheMoon.png").toExternalForm();
    }
}
