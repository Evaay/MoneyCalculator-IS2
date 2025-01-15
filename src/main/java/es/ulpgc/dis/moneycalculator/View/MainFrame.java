package es.ulpgc.dis.moneycalculator.View;

import es.ulpgc.dis.moneycalculator.Control.Command;
import es.ulpgc.dis.moneycalculator.View.swing.SwingCurrencyDialog;
import es.ulpgc.dis.moneycalculator.View.swing.SwingMoneyDialog;
import es.ulpgc.dis.moneycalculator.View.swing.SwingMoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.BoxLayout.Y_AXIS;

public class MainFrame extends JFrame{
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public MainFrame() throws HeadlessException {
        this.setTitle("Money calculator");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), Y_AXIS));

        this.add(createMoneyDialog());
        this.add(createPanelTo());
        this.add(createPanelMoneyResult());
    }

    private Component createPanelTo() {
        JPanel panelFieldTo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFieldTo.add(new JLabel("To"));
        panelFieldTo.add(createCurrencyDialog());
        return panelFieldTo;
    }

    private Component createPanelMoneyResult() {
        JPanel panelShowMoney = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelShowMoney.add(toolbar());
        panelShowMoney.add(createMoneyDisplay());
        return panelShowMoney;
    }

    private Component toolbar() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("exchangeMoney").execute());
        return button;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }
}
