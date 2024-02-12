package br.com.mmmsieto.laziness;

public class ApplePay {
    private final String email;

    ApplePay(String email) {
        this.email = email;

        System.out.println("Building Apple pay");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Apple pay built");

    }

    public String getEmail() {
        return email;
    }

}