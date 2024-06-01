package org.cdh.ui;

import static org.cdh.ui.CalculatorUI.REGEX;
import static org.cdh.ui.CalculatorUI.addToDisplay;
import static org.cdh.ui.CalculatorUI.flag;
import static org.cdh.ui.CalculatorUI.inputScreen;
import static org.cdh.ui.CalculatorUI.selectedOperator;
import static org.cdh.ui.CalculatorUI.typedValue;
import static org.cdh.ui.Controller.calculate;

import java.util.regex.Pattern;

public interface OperatorButtonAction {
    static void buttonActionSetup(char c) {
        if (!Pattern.matches(REGEX, inputScreen.getText()))
            return;
        if (flag) {
            typedValue = calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
            if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                inputScreen.setText(String.valueOf((int) typedValue));
            } else {
                inputScreen.setText(String.valueOf(typedValue));
            }
            flag = false;
            addToDisplay = false;
        }
        selectedOperator = c;
    }
}