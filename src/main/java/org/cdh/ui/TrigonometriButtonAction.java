package org.cdh.ui;

import static org.cdh.ui.CalculatorUI.REGEX;
import static org.cdh.ui.CalculatorUI.addToDisplay;
import static org.cdh.ui.CalculatorUI.flag;
import static org.cdh.ui.CalculatorUI.inputScreen;
import static org.cdh.ui.CalculatorUI.selectedOperator;
import static org.cdh.ui.CalculatorUI.typedValue;

import java.util.regex.Pattern;

public interface TrigonometriButtonAction {
    static void buttonActionSetup(String trigonometri) {
        if (!Pattern.matches(REGEX, inputScreen.getText()))
            return;
        if (flag) {
            switch (trigonometri) {
                case "sin" -> {
                    typedValue = Math.sin(Double.parseDouble(inputScreen.getText()));
                }
                case "cos" -> {
                    typedValue = Math.cos(Double.parseDouble(inputScreen.getText()));
                }
                case "tan" ->{
                    typedValue = Math.tan(Double.parseDouble(inputScreen.getText()));
                }
                case "tanh" ->{
                    typedValue = Math.tanh(Double.parseDouble(inputScreen.getText()));
                }
                case "sqrt" -> {
                    typedValue = Math.sqrt(Double.parseDouble(inputScreen.getText()));
                }
                default ->{
                    throw new IllegalArgumentException("please enter correct trigonometri operator");
                }
            }
            if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                inputScreen.setText(String.valueOf((int) typedValue));
            } else {
                inputScreen.setText(String.valueOf(typedValue));
            }
            selectedOperator = ' ';
            addToDisplay = false;
        }
    }
}
