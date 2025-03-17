package edu.vanier.superspace.controllers;

import edu.vanier.superspace.simulation.Simulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ControlBarFXMLController {
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
}
