package br.com.mmmsieto.highOrderFunction.impl;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class HighOrderFunction {

    public static void main(String[] args) {

        // Anonymous class
        Function<Integer, String> intParaString = new Function<>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }
        };

        // Lambda
        Function<Integer, String> intParaStringLambda = integer -> integer.toString();

        Stream.of(1, 2, 3, 4, 5).map(intParaString);
        Stream.of(1, 2, 3, 4, 5).map(intParaStringLambda);


        // BiFunction<T, U, R> - Lambda
        BiFunction<String, Integer, String> apresentacao = (nome, idade) -> "Olá, meu nome é " + nome + " e tenho " + idade + " anos.";

        System.out.println(apresentacao.apply("Márcio", 41));

        // BiFunction<T, U, R> - Anonymous class
        BiFunction<String, Integer, String> apresentacaoAnonima = new BiFunction<>() {
            @Override
            public String apply(String nome, Integer idade) {
                return "Olá, meu nome é " + nome + " e tenho " + idade + " anos.";
            }
        };

        System.out.println(apresentacaoAnonima.apply("Márcio", 41));
    }

}
