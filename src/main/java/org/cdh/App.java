package org.cdh;

import javax.swing.SwingUtilities;

import org.cdh.ui.CalculatorUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorUI();
        });
    }
}
