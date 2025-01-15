package es.ulpgc.dis.moneycalculator.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import es.ulpgc.dis.moneycalculator.Model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FixerCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try {
            return toList(loadJsonCode());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String jsonCode) throws IOException {
        List<Currency> list = new ArrayList<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(jsonCode, JsonObject.class).get("rates").getAsJsonObject().asMap();
        Map<String, JsonElement> symbolsName = new Gson().fromJson(loadJsonCodeName(), JsonObject.class).get("symbols").getAsJsonObject().asMap();

        for (String symbol : symbols.keySet())
            list.add(new Currency(symbol, getSymbolName(symbol, symbolsName), getRateAsDouble(symbol, symbols)));
        return list;
    }

    private String getSymbolName(String symbol, Map<String, JsonElement> symbolsName) throws IOException {
        return String.valueOf(symbolsName.get(symbol)).replace("\"", "");
    }

    private double getRateAsDouble(String symbol, Map<String, JsonElement> symbols){
        return Double.parseDouble(symbols.get(symbol).getAsString());
    }

    private String loadJsonCode() throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/v1/latest?access_key=" + FixerAPI.keyCode);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private String loadJsonCodeName() throws IOException {
        URL url = new URL("https://data.fixer.io/api/symbols?access_key=" + FixerAPI.keyCodeName);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
