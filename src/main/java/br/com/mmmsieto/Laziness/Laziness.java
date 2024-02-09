package br.com.mmmsieto.Laziness;

import java.util.Optional;

public class Laziness {

    public static void main(String[] args) {

        System.out.println("Making payment");

        makePayment(new Card("5624"), () -> new ApplePay("email@gmail.com"));

        System.out.println("Paymento made");

        System.out.println("********************************************************");

        System.out.println("Making payment");

        makePayment(new Card("5628"), () -> new ApplePay("email@gmail.com"));

        System.out.println("Paymento made");

        System.out.println("********************************************************");

        String id = Optional.of("123").orElseGet(() -> {
            System.out.println("Generating an id");
            return "569";
        });

        System.out.println("id: " + id);

        System.out.println("********************************************************");

        String generatedId = Optional.<String>empty().orElseGet(() -> {
            System.out.println("Generating an id");
            return "569";
        });

        System.out.println("id: " + generatedId);

    }

    public static void makePayment(Card card, Supplier<ApplePay> applePay) {
        if (card.valid()) {
            System.out.println("Successful card payment: " + card.getNumber());
        } else {
            System.out.println("Successful Apple Pay payment: " + applePay.get().getEmail());
        }
    }

}





