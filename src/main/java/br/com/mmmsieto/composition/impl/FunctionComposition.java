package br.com.mmmsieto.composition.impl;


import br.com.mmmsieto.composition.Function;

public class FunctionComposition {

    public static void main(String[] args) {

        Function<String, String> forUnderscore = s -> s.replaceAll(" ", "_");
        Function<String, String> forLowerCase = s -> s.toLowerCase();

        System.out.println(forUnderscore.apply(forLowerCase.apply("Aula de Composição de Funcoes")));

        Function<String, String> forSneakCase = Function.compose(forUnderscore, forLowerCase);

        System.out.println(forSneakCase.apply("Aula de Composição de Funcoes"));


        Function<Integer, String> integerStringFunction = i -> i.toString();
        Function<Double, Integer> doubleIntegerFunction = d -> d.intValue();

        Function<Double, String> doubleStringFunction = Function.compose2(integerStringFunction, doubleIntegerFunction);

        System.out.println(doubleStringFunction.apply(3.2));

        Function<Double, String> doubleStringFunctiongeneric = Function.composegeneric(integerStringFunction, doubleIntegerFunction);
        System.out.println(doubleStringFunctiongeneric.apply(3.2));


        Function<Double, String> doubleStringFunction1 = integerStringFunction.compose(doubleIntegerFunction);
        System.out.println(doubleStringFunction1.apply(3.3));

    }

}
