package br.com.mmmsieto.laziness;

public class Card {
    private final String number;

    public Card(final String number) {
        this.number = number;
    }

    boolean valid() {
        return number.endsWith("4");
    }

    public String getNumber() {
        return number;
    }

}
