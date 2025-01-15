package es.ulpgc.dis.moneycalculator;

import es.ulpgc.dis.moneycalculator.Control.Command;
import es.ulpgc.dis.moneycalculator.Control.ExchangeMoneyCommand;
import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.View.MainFrame;
import es.ulpgc.dis.moneycalculator.io.FixerCurrencyLoader;
import es.ulpgc.dis.moneycalculator.io.FixerExchangeRateLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                mainFrame.getMoneyDialog().define(currencies),
                mainFrame.getCurrencyDialog().define(currencies),
                new FixerExchangeRateLoader(),
                mainFrame.getMoneyDisplay());
        mainFrame.add("exchangeMoney", command);
        mainFrame.setVisible(true);
    }
}
