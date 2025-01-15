package es.ulpgc.dis.moneycalculator.View;

import es.ulpgc.dis.moneycalculator.Model.Currency;

import java.util.List;

public interface CurrencyDialog {
    CurrencyDialog define(List<Currency> currencies);
    Currency get();
}
