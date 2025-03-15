package edu.vanier.superspace.utils;

import javafx.scene.control.TextField;

public class InputValidator {
    public double validateDouble(double oldValue, TextField inputFieldToCheck) {
        return validateDouble(oldValue, inputFieldToCheck, "%.2f");
    }

    public double validateDouble(double oldValue, TextField inputFieldToCheck, String inputFieldFormatter) {
        return 0;
    }
}
