package edu.vanier.superspace.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class InputValidator {
    public static double validateDoubleWithUserData(TextField field) {
        return validateDoubleWithUserData(field, "%.2f");
    }

    public static double validateDoubleWithUserData(TextField field, String inputFieldFormatter) {
        if (field.getUserData() == null) {
            field.setUserData(0.0);
        }

        double oldValue = (double)field.getUserData();
        double newValue = InputValidator.validateDouble(oldValue, field, inputFieldFormatter);
        field.setUserData(newValue);
        return newValue;
    }

    public static double validateDouble(double oldValue, TextField inputFieldToCheck) {
        return InputValidator.validateDouble(oldValue, inputFieldToCheck, "%.2f");
    }

    public static double validateDouble(double oldValue, TextField inputFieldToCheck, String inputFieldFormatter) {
        double newValue;
        try {
            newValue = Double.parseDouble(inputFieldToCheck.getText());
            inputFieldToCheck.setText(String.format(inputFieldFormatter, newValue));
            return newValue;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong double format!");
            alert.setHeaderText(null);
            alert.setContentText("You should input a valid double value, meaning that there is no text.");
            alert.show();
        }

        inputFieldToCheck.setText(String.format(inputFieldFormatter, oldValue));
        return oldValue;
    }
}
