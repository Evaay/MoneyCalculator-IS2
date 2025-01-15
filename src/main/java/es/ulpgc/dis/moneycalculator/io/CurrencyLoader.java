package es.ulpgc.dis.moneycalculator.io;

import es.ulpgc.dis.moneycalculator.Model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> load();
}
