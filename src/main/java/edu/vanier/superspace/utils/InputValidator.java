package edu.vanier.superspace.utils;

import edu.vanier.superspace.simulation.Input;
import javafx.scene.control.TextField;

public class InputValidator {
    public static double validateDouble(TextField field) {
        return validateDouble(field, "%.2f");
    }

    public static double validateDouble(TextField field, String inputFieldFormatter) {
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
            // Nothing yet?
        }

        inputFieldToCheck.setText(String.format(inputFieldFormatter, oldValue));
        return oldValue;
    }
}
