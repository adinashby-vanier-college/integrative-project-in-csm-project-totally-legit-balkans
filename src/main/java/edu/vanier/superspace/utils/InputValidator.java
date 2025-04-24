package edu.vanier.superspace.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Validates certain input types
 */
public class InputValidator {
    /**
     * Validates the user data based on a TextField
     * @param field the text field
     * @return validated double
     */
    public static double validateDoubleWithUserData(TextField field) {
        return validateDoubleWithUserData(field, "%.2f");
    }

    /**
     * Validates the double with user data
     * @param field the text field
     * @param inputFieldFormatter format of the double
     * @return the double formatted and validated
     */
    public static double validateDoubleWithUserData(TextField field, String inputFieldFormatter) {
        if (field.getUserData() == null) {
            field.setUserData(0.0);
        }

        double oldValue = (double)field.getUserData();
        double newValue = InputValidator.validateDouble(oldValue, field, inputFieldFormatter);
        field.setUserData(newValue);
        return newValue;
    }

    /**
     * Validates a double value
     * @param oldValue the old double value
     * @param inputFieldToCheck the field to be checked
     * @return validated value and its correct format
     */
    public static double validateDouble(double oldValue, TextField inputFieldToCheck) {
        return InputValidator.validateDouble(oldValue, inputFieldToCheck, "%.2f");
    }

    /**
     * Validates a double value
     * @param oldValue the old value
     * @param inputFieldToCheck the input field that needs to be checked
     * @param inputFieldFormatter the format of the double
     * @return the formatted and validated double
     */
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
