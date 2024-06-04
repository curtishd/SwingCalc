package org.cdh.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import org.cdh.ui.TrigonometriButtonAction.MathAction;

import static org.cdh.ui.CalculatorUI.inputScreen;
import static org.cdh.ui.CalculatorUI.flag;
import static org.cdh.ui.CalculatorUI.typedValue;
import static org.cdh.ui.CalculatorUI.selectedOperator;
import static org.cdh.ui.CalculatorUI.addToDisplay;

public enum Controller implements ActionListener {
    BUTTON_LOG() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Double number = Double.valueOf(inputScreen.getText());
            double log = Math.log(number);
            inputScreen.setText(String.valueOf(log));
        }
    },
    BUTTON_0() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(0);
        }
    },
    BUTTON_1() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(1);
        }
    },
    BUTTON_2() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(2);

        }
    },
    BUTTON_3() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(3);

        }
    },
    BUTTON_4() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(4);

        }
    },
    BUTTON_5() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(5);

        }
    },
    BUTTON_6() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(6);

        }
    },
    BUTTON_7() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(7);

        }
    },
    BUTTON_8() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(8);

        }
    },
    BUTTON_9() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.buttonActionSetup(9);

        }
    },
    BUTTON_POWER() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('^');
        }
    },
    BUTTON_ROOT() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrigonometriButtonAction.buttonActionSetup(MathAction.SQRT);
        }
    },
    BUTTON_EQUAL() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('=');
        }
    },
    BUTTON_ADD() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('+');
        }
    },
    BUTTON_SUB() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('-');

        }
    },
    BUTTON_MUL() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('*');

        }
    },
    BUTTON_MOD() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('%');

        }
    },
    BUTTON_DIV() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorButtonAction.buttonActionSetup('/');

        }
    },
    BUTTON_POINT() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (addToDisplay) {
                if (!inputScreen.getText().contains(".")) {
                    inputScreen.setText(inputScreen.getText().concat("."));
                }
            } else {
                inputScreen.setText("0.");
                addToDisplay = true;
            }
            flag = true;
        }
    },
    BUTTON_C() {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputScreen.setText("0");
            selectedOperator = ' ';
            typedValue = 0;
        }
    },
    BUTTON_E() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.constantButtonActionSetup(Math.E);

        }
    },
    BUTTON_PI() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumberButtonAction.constantButtonActionSetup(Math.PI);

        }
    },
    BUTTON_SIN() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrigonometriButtonAction.buttonActionSetup(MathAction.SIN);

        }
    },
    BUTTON_COS() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrigonometriButtonAction.buttonActionSetup(MathAction.COS);

        }
    },
    BUTTON_TAN() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrigonometriButtonAction.buttonActionSetup(MathAction.TAN);

        }
    },
    BUTTON_TANH() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrigonometriButtonAction.buttonActionSetup(MathAction.TANH);

        }
    },
    BUTTON_BACK() {
        @Override
        public void actionPerformed(ActionEvent e) {
            var str = inputScreen.getText();
            var str2 = new StringBuilder();
            for (int i = 0; i < str.length() - 1; i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inputScreen.setText("0");
            } else {
                inputScreen.setText(str2.toString());
            }
        }
    };

    static double calculate(double firstNumber, double secondNumber, char operator) {
        var f = BigDecimal.valueOf(firstNumber);
        var s = BigDecimal.valueOf(secondNumber);
        return switch (operator) {
            case '+' -> {
                yield f.add(s).doubleValue();
            }

            case '-' -> {
                yield f.subtract(s).doubleValue();
            }

            case '*' -> {
                yield f.multiply(s).doubleValue();
            }

            case '/' -> {
                yield firstNumber / secondNumber;
            }

            case '%' -> {
                yield f.remainder(s).doubleValue();
            }

            case '^' -> {
                yield Math.pow(firstNumber, secondNumber);
            }

            default -> {
                yield secondNumber;
            }
        };
    }
}
