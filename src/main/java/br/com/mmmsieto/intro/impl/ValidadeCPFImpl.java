package br.com.mmmsieto.intro.impl;

import br.com.mmmsieto.intro.ValidadeCPF;

public class ValidadeCPFImpl {

    public static void main(String[] args) {

        // Anonymous class
        ValidadeCPF validadeCPFAnonymous = new ValidadeCPF() {
            @Override
            public Boolean apply(String cpf) {
                return ValidadeCPF.validaCPF(cpf);
            }
        };

        System.out.println("Anonymous class");
        System.out.println(validadeCPFAnonymous.apply("12345678909"));
        System.out.println("---------------------------------");


        // Lambda
        ValidadeCPF validadeCPFLambda = ValidadeCPF::validaCPF;

        System.out.println("Lambda");
        System.out.println(validadeCPFLambda.apply("12345678909"));
        System.out.println("---------------------------------");
    }
}
