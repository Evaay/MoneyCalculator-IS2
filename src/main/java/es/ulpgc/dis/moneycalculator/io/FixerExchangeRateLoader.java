package es.ulpgc.dis.moneycalculator.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import es.ulpgc.dis.moneycalculator.Model.Currency;
import es.ulpgc.dis.moneycalculator.Model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;


public class FixerExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            Map<String, JsonElement> symbolsValue = new Gson().fromJson(loadJsonCode(), JsonObject.class).get("rates").getAsJsonObject().asMap();
            return new ExchangeRate(from, to, LocalDate.now(), getRateAsDouble(symbolsValue.get(from.code()).getAsString(), symbolsValue.get(to.code()).getAsString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadJsonCode() throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/v1/latest?access_key=" + FixerAPI.keyValue);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private double getRateAsDouble(String symbolFrom, String symbolTo){
        return Double.parseDouble(symbolTo)/Double.parseDouble(symbolFrom);
    }
}
