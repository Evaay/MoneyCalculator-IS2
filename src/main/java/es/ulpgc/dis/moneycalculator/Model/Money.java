package es.ulpgc.dis.moneycalculator.Model;

public record Money(double amount, Currency currency) {
    @Override
    public String toString() {
        return changeAmountFormat();
    }

    private String changeAmountFormat() {
        return String.format("%.2f %s", amount, currency);
    }
}
