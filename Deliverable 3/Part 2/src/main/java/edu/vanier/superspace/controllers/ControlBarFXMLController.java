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

    public ControlBarFXMLController() {
        instance = this;
        selectedEntity = null;
    }

    public void updateSelectedEntity(Entity entity) {
        selectedEntity = entity;
        planetName.setText(entity.getAstralBody().getName());
        description.setText(entity.getAstralBody().getDescription());

    }

    @FXML
    private void onPause(ActionEvent event) {
        Simulation.getInstance().Stop();
    }

    @FXML
    private void onZoom(ActionEvent event) {

    }

    @FXML
    private void onPlay(ActionEvent event) {
        Simulation.getInstance().Run();
    }

    @FXML
    private void onFastForward(ActionEvent event) {
        Simulation.getInstance().Step();
    }

    @Override
    public void onUpdate(double deltaTime) {
        if (selectedEntity != null) {
            txtOrbitTime.setText(String.format("%d", Simulation.getInstance().getClock().getElapsedTime()));
            txtAvgSpeed.setText(String.format("%.2f", selectedEntity.getRigidBody().getVelocity().magnitude()));
        }
    }
}
