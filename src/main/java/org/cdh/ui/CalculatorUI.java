package org.cdh.ui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class CalculatorUI {
    final static String REGEX = "([-]?\\d+[.]\\d*)|(\\d+)|(-\\d+)";
    private final int WINDOW_WIDTH = 410;
    private final int WINDOW_HEIGHT = 600;
    private final int BUTTON_WIDTH = 80;
    private final int BUTTON_HEIGHT = 70;
    private final int MARGIN_X = 20;
    private final int MARGIN_Y = 60;

    private final JFrame window;
    private static JComboBox<String> comboCalculatorType, themeBox;
    static JTextField inputScreen;
    static JButton btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd,
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnPoint, btnEqual, btnRoot, btnPower, btnLog, btnSin, btnCos, btnTan, btnTanh, btnE, btnPI;
    private static JLabel themeLabel = new JLabel("Theme");
    static char selectedOperator = ' ';
    static boolean flag = true; //
    static boolean addToDisplay = true; //
    static double typedValue = 0;
    private final int[] columns = {
            MARGIN_X,
            MARGIN_X + 90,
            MARGIN_X + 90 * 2,
            MARGIN_X + 90 * 3,
            MARGIN_X + 90 * 4,
            MARGIN_X + 90 * 5,
    };
    private final int[] rows = {
            MARGIN_Y, MARGIN_Y + 100,
            MARGIN_Y + 100 + 80,
            MARGIN_Y + 100 + 80 * 2,
            MARGIN_Y + 100 + 80 * 3,
            MARGIN_Y + 100 + 80 * 4,
            MARGIN_Y + 100 + 80 * 5,
    };

    public CalculatorUI() {
        FlatLightLaf.setup();
        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);

        initInputScreen(columns, rows);
        initButtons(columns, rows);
        initCalculatorTypeSelector();
        initThemeSelector();

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private void initThemeSelector() {
        themeBox = new JComboBox<String>(new String[] { "Light", "Intellij", "Dark" });
        themeLabel.setBounds(230, 10, 40, 25);
        window.add(themeLabel);
        themeBox.setBounds(230, 30, 140, 25);
        themeBox.setToolTipText("Theme Selector");
        window.add(themeBox);
        themeBox.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            var selectedOne = (String) event.getItem();
            switch (selectedOne) {
                case "Light" -> {
                    try {
                        UIManager.setLookAndFeel(new FlatLightLaf());
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    FlatLaf.updateUI();
                }
                case "Dark" -> {
                    try {
                        UIManager.setLookAndFeel(new FlatDarkLaf());
                        FlatLaf.updateUI();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                }
                case "Intellij" -> {
                    try {
                        UIManager.setLookAndFeel(new FlatIntelliJLaf());
                        FlatLaf.updateUI();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initCalculatorTypeSelector() {
        comboCalculatorType = createComboBox(new String[] { "Standard", "Scientific" }, 20, 30, "Calculator type");
        comboCalculatorType.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            var selectedItem = (String) event.getItem();
            switch (selectedItem) {
                case "Standard" -> {
                    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                    inputScreen.setBounds(columns[0], rows[0], 350, 70);
                    btnRoot.setVisible(false);
                    btnPower.setVisible(false);
                    btnLog.setVisible(false);
                    btnE.setVisible(false);
                    btnSin.setVisible(false);
                    btnCos.setVisible(false);
                    btnTan.setVisible(false);
                    btnTanh.setVisible(false);
                    btnPI.setVisible(false);
                    break;
                }
                case "Scientific" -> {
                    window.setSize(WINDOW_WIDTH + 80 * 2, WINDOW_HEIGHT);
                    inputScreen.setSize(350 + 80 * 2, 70);
                    btnRoot.setVisible(true);
                    btnPower.setVisible(true);
                    btnLog.setVisible(true);
                    btnE.setVisible(true);
                    btnSin.setVisible(true);
                    btnCos.setVisible(true);
                    btnTan.setVisible(true);
                    btnTanh.setVisible(true);
                    btnPI.setVisible(true);
                    break;
                }
            }
        });
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, String tooltip) {
        var combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(tooltip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        window.add(combo);
        return combo;
    }

    private void initButtons(int[] columns, int[] rows) {
        btnC = createButton("C", columns[0], rows[1]);
        btnC.addActionListener(Controller.BUTTON_C);

        btnBack = createButton("<-", columns[1], rows[1]);
        btnBack.addActionListener(Controller.BUTTON_BACK);

        btnMod = createButton("%", columns[2], rows[1]);
        btnMod.addActionListener(Controller.BUTTON_MOD);

        btnDiv = createButton("/", columns[3], rows[1]);
        btnDiv.addActionListener(Controller.BUTTON_DIV);

        btn7 = createButton("7", columns[0], rows[2]);
        btn7.addActionListener(Controller.BUTTON_7);

        btn8 = createButton("8", columns[1], rows[2]);
        btn8.addActionListener(Controller.BUTTON_8);

        btn9 = createButton("9", columns[2], rows[2]);
        btn9.addActionListener(Controller.BUTTON_9);

        btnMul = createButton("*", columns[3], rows[2]);
        btnMul.addActionListener(Controller.BUTTON_MUL);

        btn4 = createButton("4", columns[0], rows[3]);
        btn4.addActionListener(Controller.BUTTON_4);

        btn5 = createButton("5", columns[1], rows[3]);
        btn5.addActionListener(Controller.BUTTON_5);

        btn6 = createButton("6", columns[2], rows[3]);
        btn6.addActionListener(Controller.BUTTON_6);

        btnSub = createButton("-", columns[3], rows[3]);
        btnSub.addActionListener(Controller.BUTTON_SUB);

        btn1 = createButton("1", columns[0], rows[4]);
        btn1.addActionListener(Controller.BUTTON_1);

        btn2 = createButton("2", columns[1], rows[4]);
        btn2.addActionListener(Controller.BUTTON_2);

        btn3 = createButton("3", columns[2], rows[4]);
        btn3.addActionListener(Controller.BUTTON_3);

        btnAdd = createButton("+", columns[3], rows[4]);
        btnAdd.addActionListener(Controller.BUTTON_ADD);

        btnPoint = createButton(".", columns[0], rows[5]);
        btnPoint.addActionListener(Controller.BUTTON_POINT);

        btn0 = createButton("0", columns[1], rows[5]);
        btn0.addActionListener(Controller.BUTTON_0);

        btnEqual = createButton("=", columns[2], rows[5]);
        btnEqual.addActionListener(Controller.BUTTON_EQUAL);
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);

        btnRoot = createButton("\u221a", columns[4], rows[1]);
        btnRoot.addActionListener(Controller.BUTTON_ROOT);
        btnRoot.setVisible(false);

        btnPower = createButton("pow", columns[4], rows[2]);
        btnPower.addActionListener(Controller.BUTTON_POWER);
        btnPower.setVisible(false);

        btnLog = createButton("ln", columns[4], rows[3]);
        btnLog.addActionListener(Controller.BUTTON_LOG);
        btnLog.setVisible(false);

        btnE = createButton("E", columns[4], rows[4]);
        btnE.addActionListener(Controller.BUTTON_E);
        btnE.setVisible(false);

        btnPI = createButton("\u03c0", columns[4], rows[5]);
        btnPI.addActionListener(Controller.BUTTON_PI);
        btnPI.setVisible(false);

        btnSin = createButton("sin", columns[5], rows[1]);
        btnSin.addActionListener(Controller.BUTTON_SIN);
        btnSin.setVisible(false);

        btnCos = createButton("cos", columns[5], rows[2]);
        btnCos.addActionListener(Controller.BUTTON_COS);
        btnCos.setVisible(false);

        btnTan = createButton("tan", columns[5], rows[3]);
        btnTan.addActionListener(Controller.BUTTON_TAN);
        btnTan.setVisible(false);

        btnTanh = createButton("tanh", columns[5], rows[4]);
        btnTanh.addActionListener(Controller.BUTTON_TANH);
        btnTanh.setVisible(false);
    }

    private JButton createButton(String label, int x, int y) {
        var btn = new JButton(label);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        window.add(btn);
        return btn;
    }

    private void initInputScreen(int[] columns, int[] rows) {
        inputScreen = new JTextField("0");
        inputScreen.setBounds(columns[0], rows[0], 350, 70);
        inputScreen.setEditable(false);
        inputScreen.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));

        window.add(inputScreen);
    }
}
