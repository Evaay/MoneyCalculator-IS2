package es.ulpgc.dis.moneycalculator.io;

import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.Model.ExchangeRate;

import java.time.LocalDate;


public class FixerExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(from, to, LocalDate.now(), to.rate()/from.rate());
    }
}
