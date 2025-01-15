package es.ulpgc.dis.moneycalculator.io;

import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.Model.ExchangeRate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
