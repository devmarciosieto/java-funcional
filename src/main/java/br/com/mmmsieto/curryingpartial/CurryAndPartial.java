package br.com.mmmsieto.curryingpartial;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class CurryAndPartial {

    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> adiciona = (a, b) -> a + b;
        System.out.println(adiciona.apply(2, 3));

        // Curry
        Function<Integer, Function<Integer, Integer>> adicionaCurried0 = integer0 -> integer1 -> integer0  + integer1;
        System.out.println(adicionaCurried0.apply(10).apply(10));

        Function<Integer, Integer> adicionaCurried1 = adicionaCurried0.apply(30);
        System.out.println(adicionaCurried1.apply(10));

        BiFunction<String, Integer, Double> calculaTaxa =
                (estado, preco) -> "SP".equals(estado) ? preco * 0.1 : preco * 0.2;

        System.out.println(calculaTaxa.apply("SP", 10));
        System.out.println(calculaTaxa.apply("MG", 10));

        Function<String, Function<Integer, Double>> calculaTaxaCurried =
                estado -> preco -> "SP".equals(estado) ? preco * 0.1 : preco *0.2;

        Function<Integer, Double> calculaTaxaSP = calculaTaxaCurried.apply("SP");

        System.out.println("---------------------------------");
        Stream.of(10, 20, 30)
                .map(calculaTaxaSP)
                .forEach(System.out::println);

        System.out.println("---------------------------------");
        Function<String, Function<Integer, Double>> calculaTaxaCurriedFunction = curry(calculaTaxa);
        System.out.println(calculaTaxaCurriedFunction.apply("SP").apply(10));

        System.out.println("---------------------------------");
        Function<Integer, Double> calculaTaxaSPParcial = partial0("SP", calculaTaxa);
        System.out.println(calculaTaxaSPParcial.apply(80));

        System.out.println("------------- SP -------------------");

        Function<String, Double> calculaTaxaPrecoSP10 = partial1(10, calculaTaxa);
        System.out.println(calculaTaxaPrecoSP10.apply("SP"));

        System.out.println("------------- RJ --------------------");

        Function<String, Double> calculaTaxaPrecoRJ10 = partial1(10, calculaTaxa);
        System.out.println(calculaTaxaPrecoRJ10.apply("RJ"));

    }


    static <I, D, S> Function<I, D> partial0(S estado, BiFunction<S, I, D> f) {
        return i -> f.apply(estado, i);
    }

    static <I, D, S> Function<S, D> partial1(I preco, BiFunction<S, I, D> f) {
        return s -> f.apply(s, preco);
    }

    static <S, I, D> Function<S, Function<I, D>> curry(BiFunction<S, I, D> f) {
        return s -> i -> f.apply(s, i);
    }

}
