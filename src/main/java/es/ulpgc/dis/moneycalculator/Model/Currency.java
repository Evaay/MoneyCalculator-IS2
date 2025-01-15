package es.ulpgc.dis.moneycalculator.Model;

public record Currency(String code, String nameCode, double rate) {
    @Override
    public String toString() {
        return code + " - " + nameCode;
    }
}
