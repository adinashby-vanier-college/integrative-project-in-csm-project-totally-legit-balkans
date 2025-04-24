package edu.vanier.superspace.controllers;

import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.Tickable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * FXML Controller class for the control bar.
 */
public class ControlBarFXMLController implements Tickable {
    @Getter
    private static ControlBarFXMLController instance;
    private Entity selectedEntity;
    @FXML
    private Button pauseButton;
    @FXML
    private Label planetName;
    @FXML
    private Label massIndicator;
    @FXML
    private Label speedIndicator;
    @FXML
    private Button zoomButton;
    @FXML
    private Button playButton;
    @FXML
    private Button fastForwardButton;
    @FXML
    private Text description;
    @FXML
    private Text txtAvgSpeed;
    @FXML
    private Text txtOrbitTime;

    /**
     * Default constructor
     */
    public ControlBarFXMLController() {
        instance = this;
        selectedEntity = null;
    }

    /**
     * Updates the values of the selected entity
     * @param entity the selected entity
     */
    public void updateSelectedEntity(Entity entity) {
        selectedEntity = entity;
        planetName.setText(entity.getAstralBody().getName());
        description.setText(entity.getAstralBody().getDescription());

    }

    /**
     * Action happens when the simulation is paused.
     * @param event the pause button pressed event
     */
    @FXML
    private void onPause(ActionEvent event) {
        Simulation.getInstance().Stop();
    }

    /**
     * Action happens when the user zooms.
     * @param event user zooms event
     */
    @FXML
    private void onZoom(ActionEvent event) {

    }

    /**
     * Actions happens when the simulation is played
     * @param event user presses the play button event
     */
    @FXML
    private void onPlay(ActionEvent event) {
        Simulation.getInstance().Run();
    }

    /**
     * Action happens when the fast-forward button is pressed.
     * @param event user presses fast-forward button event
     */
    @FXML
    private void onFastForward(ActionEvent event) {
        Simulation.getInstance().Step();
    }

    /**
     * Happens on each update because this class is a part of the Tickable interface.
     * @param deltaTime delta time
     */
    @Override
    public void onUpdate(double deltaTime) {
        if (selectedEntity != null) {
            txtOrbitTime.setText(String.format("%d", Simulation.getInstance().getClock().getElapsedTime()));
            txtAvgSpeed.setText(String.format("%.2f", selectedEntity.getRigidBody().getVelocity().magnitude()));
        }
    }
}
