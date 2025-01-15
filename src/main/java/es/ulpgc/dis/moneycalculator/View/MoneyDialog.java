package es.ulpgc.dis.moneycalculator.View;

import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.Model.Money;

import java.util.List;

public interface MoneyDialog {
    MoneyDialog define(List<Currency> currencies);
    Money get();
}
