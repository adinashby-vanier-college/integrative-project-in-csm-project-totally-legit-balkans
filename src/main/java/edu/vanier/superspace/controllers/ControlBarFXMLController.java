package edu.vanier.superspace.controllers;

import com.google.errorprone.annotations.FormatMethod;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.Tickable;
import edu.vanier.superspace.simulation.components.Camera;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

public class ControlBarFXMLController {
    @Getter
    private static ControlBarFXMLController instance;

    @FXML
    private Text timeFeedback;
    @FXML
    private Label planetName;
    @FXML
    private Text description;
    @FXML
    private Text txtOrbitTime;
    @FXML
    private Text txtAvgSpeed;
    @FXML @Getter
    private Slider timeMultiplier;
    @FXML @Getter
    private Slider zoomSlider;

    private Entity selectedEntity;

    @FXML
    private void initialize() {
        ControlBarFXMLController.instance = this;
        timeMultiplier.valueProperty().addListener(this::onTimeMultiplierMoved);
        zoomSlider.valueProperty().addListener(this::onZoomMultiplierMoved);
    }

    public void onPause(ActionEvent event) {
        Simulation.getInstance().stop();
    }

    public void onPlay(ActionEvent event) {
        Simulation.getInstance().run();
    }

    public void onTick(ActionEvent event) {
        Simulation.getInstance().getClock().step();
    }

    public void onUpdate(double deltaTime) {

    }

    public void selectEntity(Entity entity) {
        selectedEntity = entity;
        planetName.setText(entity.getAstralBody().getName());
        description.setText(entity.getAstralBody().getDescription());
    }

    private void onTimeMultiplierMoved(Observable observable, Number oldValue, Number newValue) {
        timeFeedback.setText(getTimeString((int)timeMultiplier.getValue()));
    }

    private void onZoomMultiplierMoved(Observable observable, Number oldValue, Number newValue) {
        Camera.getInstance().setZoom(newValue.doubleValue());
    }

    private String getTimeString(int timeExponent) {
        return switch (timeExponent) {
            case 0 -> "Real time";
            case 1 -> "10 s / s";
            case 2 -> "2 min / s";
            case 3 -> "20 min / s";
            case 4 -> "3 hr / s";
            case 5 -> "1 day / s";
            case 6 -> "2 wk / s";
            case 7 -> "4 mo / s";
            case 8 -> "3 yr / s";
            case 9 -> "30 yr / s";
            default -> "Invalid";
        };
    }
}
