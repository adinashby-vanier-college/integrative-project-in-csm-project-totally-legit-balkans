package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Presets{
    EARTH("Earth", "A friendly planet!", "Terrestrial", 6380, 20, getEarthPath(), false),
    VENUS("Venus", "Beautifully lit planet!", "Terrestrial", 6052, 10, getVenusPath(), false),
    MERCURY("Mercury", "Small Planet!", "Terrestrial", 2439, 5, getMercuryPath(), false),
    MOON("Moon", "Earth's close friend", "Terrestrial", 1737, 10, getMoonPath(), false),
    MARS("Mars", "Big red!", "Terrestrial", 3389, 15, getMarsPath(), false),
    JUPITER("Jupiter", "Biggest planet!", "Gaseous", 69910, 10, getJupiterPath(), false),
    URANUS("Uranus", "Lol", "Ice", 25559, 10, getUranusPath(), false),
    NEPTUNE("Neptune", "darker blueee", "Ice", 24764, 10, getNeptunePath(), false),
    PLUTO("Pluto", "Wishes it were a planet...", "Terrestrial", 1188, 10, getPlutoPath(), false),
    CALLISTO("Callisto", "One of Jupiter's moons", "Terrestrial", 2410, 10, getCallistoPath(), false),
    IO("Io", "One of Jupiter's moons", "Terrestrial", 1821, 10, getIOPath(), false),
    EUROPA("Europa", "One of Jupiter's moons", "Terrestrial", 1561, 10, getEuropaPath(), false),
    SUN("Sun", "Huge star", "Star", 1.3927e6 / 2, 10, getSunPath(), false);


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
        return Presets.class.getResource("/Sprites/Planets/Moon.png").toExternalForm();
    }
}
