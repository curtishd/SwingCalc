package org.cdh.ui;

import static org.cdh.ui.CalculatorUI.addToDisplay;
import static org.cdh.ui.CalculatorUI.flag;
import static org.cdh.ui.CalculatorUI.inputScreen;

import java.util.regex.Pattern;

public interface NumberButtonAction {
    static void buttonActionSetup(String num) {
        if (addToDisplay) {
            if (Pattern.matches("[0]*", inputScreen.getText())) {
                inputScreen.setText(num);
            } else {
                inputScreen.setText(inputScreen.getText().concat(num));
            }
        } else {
            inputScreen.setText(num);
            addToDisplay = true;
        }
        flag = true;
    }

    static void constantButtonActionSetup(double cnst) {
        var cnstStr = String.format("%f", cnst);
        if (!addToDisplay) {
            addToDisplay = true;
        }
        inputScreen.setText(cnstStr);
        flag = true;
    }
}