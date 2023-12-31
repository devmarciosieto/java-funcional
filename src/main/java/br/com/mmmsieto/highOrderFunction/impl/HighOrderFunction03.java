package br.com.mmmsieto.highOrderFunction.impl;

import java.util.function.Function;

public class HighOrderFunction03 {
    public static void main(String[] args) {
        // Anonymous class

        Function<String, Function<String, String>> saudacao =
                new Function<String, Function<String, String>>() {
                    @Override
                    public Function<String, String> apply(final String saudacao) {
                        return new Function<String, String>() {
                            @Override
                            public String apply(String nome) {
                                return saudacao + ", " + nome;
                            }
                        };
                    }
                };

        Function<String, String> bomDia = saudacao.apply("Bom dia");
        String saudacaoString = bomDia.apply("Márcio");
        System.out.println(saudacaoString);

        System.out.println(saudacao.apply("Boa Tarde").apply("Márcio"));

        // Lambda

        Function<String, Function<String, String>> stringFunctionLambda =
                s -> n -> s + ", " + n;

        System.out.println(stringFunctionLambda.apply("Boa Noite").apply("Márcio"));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> f1 =
                new Function<Integer, Function<Integer, Function<Integer, Integer>>>() {
                    @Override
                    public Function<Integer, Function<Integer, Integer>> apply(final Integer i1) {
                        return new Function<Integer, Function<Integer, Integer>>() {
                            @Override
                            public Function<Integer, Integer> apply(final Integer i2) {
                                return new Function<Integer, Integer>() {
                                    @Override
                                    public Integer apply(final Integer i3) {
                                        return i1 + i2 + i3;
                                    }
                                };
                            }
                        };
                    }
                };

        Function<Integer, Function<Integer, Integer>> f2 = f1.apply(10);
        Function<Integer, Integer> f3 = f2.apply(20);
        System.out.println(f3.apply(20));

        System.out.println(f1.apply(20).apply(36).apply(32));


        Function<Integer, Function<Integer, Function<Integer, Function<Integer, Integer>>>> f1Lambda =
                a1 -> b2 -> c3 -> d4 -> a1 + b2 + c3 + d4;

        System.out.println(f1Lambda.apply(655).apply(4564).apply(41261).apply(45646));

    }
}
