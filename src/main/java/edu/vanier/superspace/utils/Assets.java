package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Assets {
    PLANET_1("Planet-1"),
    EARTH("Earth"),
    VENUS("Venus"),
    MERCURY("Mercury"),
    MARS("Mars"),
    JUPITER("Jupiter"),
    URANUS("Uranus"),
    NEPTUNE("Neptune"),
    PLUTO("Pluto"),
    CALLISTO("Callisto"),
    IO("Io"),
    EUROPA("Europa"),
    SUN("Sun"),
    MOON("Moon");
    
    private final String filePath;
    
    Assets(String filePath){
        this.filePath = "/Sprites/Planets/" + filePath + ".png";
    }
}
