package es.ulpgc.dis.moneycalculator.View.swing;

import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.Model.Money;
import es.ulpgc.dis.moneycalculator.View.CurrencyDialog;
import es.ulpgc.dis.moneycalculator.View.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.BoxLayout.Y_AXIS;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new BoxLayout(this, Y_AXIS));
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        JPanel TextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        TextFieldPanel.add(new JLabel("Amount"));
        TextFieldPanel.add(createAmountField());
        add(TextFieldPanel);

        JPanel CurrencyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        CurrencyPanel.add(new JLabel("From"));
        CurrencyPanel.add(createCurrencyDialog(currencies));
        add(CurrencyPanel);
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createAmountField() {
        JTextField textField = new JTextField();
        textField.setColumns(5);
        this.amountField = textField;
        return textField;
    }

    @Override
    public Money get() {
        double amount = validateAmount(amountField.getText());
        return new Money(amount, currencyDialog.get());
    }

    private double validateAmount(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
}
