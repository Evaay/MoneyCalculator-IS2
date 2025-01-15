package es.ulpgc.dis.moneycalculator.View.swing;

import es.ulpgc.dis.moneycalculator.Model.Money;
import es.ulpgc.dis.moneycalculator.View.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(Money money) {
        this.setText(money.toString());
    }
}
