package edu.vanier.superspace.utils;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class UserCatalog {
    private final ArrayList<AstralBody> catalog = new ArrayList<>();

    public UserCatalog() {
        addPresetsToCatalog();
        catalog.forEach(event -> {
            System.out.println(event.getPath());
        });
    }

    private void addPresetsToCatalog() {
        for (int i = 0; i < Presets.values().length; i++) {
            String name = Presets.values()[i].getName();
            String description = Presets.values()[i].getDescription();
            String type = Presets.values()[i].getType();
            double radius = Presets.values()[i].getRadius();
            double mass = Presets.values()[i].getMass();
            String path = Presets.values()[i].getPath();
            boolean isPreset = Presets.values()[i].isPreset();

            AstralBody astralBody = new AstralBody(name, description, type, radius, mass, path, isPreset);
            catalog.add(astralBody);
        }
    }

    private void loadUserSavedAstralBodies() {}

    private void refreshCatalog() {
        loadUserSavedAstralBodies();
    }

    public void addToCatalog(AstralBody body) {
        catalog.add(body);
    }
}
