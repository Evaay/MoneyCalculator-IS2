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
            return toList(loadJsonCodeName());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String jsonCode) throws IOException {
        List<Currency> list = new ArrayList<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(jsonCode, JsonObject.class).get("symbols").getAsJsonObject().asMap();

        for (String symbol : symbols.keySet())
            list.add(new Currency(symbol, getSymbolName(symbol, symbols)));
        return list;
    }

    private String getSymbolName(String symbol, Map<String, JsonElement> symbols) {
        return String.valueOf(symbols.get(symbol)).replace("\"", "");
    }

    private String loadJsonCodeName() throws IOException {
        URL url = new URL("https://data.fixer.io/api/symbols?access_key=" + FixerAPI.keyCodeName);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }


}
