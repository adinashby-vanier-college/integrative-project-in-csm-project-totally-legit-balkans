package edu.vanier.superspace.utils;

import edu.vanier.superspace.annotations.ToSerialize;
import edu.vanier.superspace.controllers.AstralCreationFXMLController;
import edu.vanier.superspace.simulation.Simulation;
import lombok.Getter;

import java.util.ArrayList;

/**
 * A user catalog that contains a catalog filled with all the planets to be loaded in the context menu in
 * the astral creation menu.
 */
@Getter
public class UserCatalog {
    private final ArrayList<AstralBody> catalog = new ArrayList<>();

    @ToSerialize
    private final ArrayList<AstralBody> customCatalog = new ArrayList<>();

    /**
     * Default constructor
     */
    public UserCatalog() {
        addPresetsToCatalog();
        catalog.forEach(event -> {
            System.out.println(event.getPath());
        });
    }

    /**
     * Adds the presets to the catalog
     */
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

    /**
     * Saves the user presets to the simulation file
     */
    public void saveUserPresetsToFile() {

    }

    /**
     * Adds an astral body to the catalog
     * @param body astral body instance
     */
    public void addToCatalog(AstralBody body) {
        Simulation.getInstance().getUserCatalog().catalog.add(body);
        Simulation.getInstance().getUserCatalog().customCatalog.add(body);
        AstralCreationFXMLController.getInstance().addToContextMenu(body);
    }

    /**
     * Removes an astral body from the catalog
     * @param body astral body instance
     */
    public void removeFromCatalog(AstralBody body) {
        Simulation.getInstance().getUserCatalog().catalog.remove(body);
        Simulation.getInstance().getUserCatalog().customCatalog.remove(body);
    }
}
