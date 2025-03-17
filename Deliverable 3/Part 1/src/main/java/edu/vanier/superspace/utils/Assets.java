package edu.vanier.superspace.utils;

import lombok.Getter;

@Getter
public enum Assets {
    PLANET_1("Planet-1");
    
    private final String filePath;
    
    Assets(String filePath){
        this.filePath = "/Sprites/Planets/" + filePath + ".png";
    }
}
