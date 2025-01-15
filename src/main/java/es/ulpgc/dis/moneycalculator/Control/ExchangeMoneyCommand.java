package es.ulpgc.dis.moneycalculator.Control;

import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.Model.ExchangeRate;
import es.ulpgc.dis.moneycalculator.Model.Money;
import es.ulpgc.dis.moneycalculator.View.CurrencyDialog;
import es.ulpgc.dis.moneycalculator.View.MoneyDialog;
import es.ulpgc.dis.moneycalculator.View.MoneyDisplay;
import es.ulpgc.dis.moneycalculator.io.ExchangeRateLoader;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();

        ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
        Money result = new Money((money.amount()*exchangeRate.rate()), currency);

        moneyDisplay.show(result);
    }
}
