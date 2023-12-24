package br.com.mmmsieto.intro.impl;

import br.com.mmmsieto.intro.CustomFunction;

public class SimpleFunctions01 {

    public static void main(String[] args) {

        // Função Integer para String
        // Classes Anonimas ou lambdas

        //Anonima
        CustomFunction<Integer, String> intToString = new CustomFunction<>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }

        };

        System.out.println("Integer para String - Anonima");
        System.out.println(intToString.apply(10));
        System.out.println("----------------------------");


        //Lambda

        CustomFunction<Integer, String> intToStringLambda = Object::toString;

        System.out.println("Integer para String - Lambda");
        System.out.println(intToStringLambda.apply(10));
        System.out.println("----------------------------");

        CustomFunction<Integer, Integer> sucessor = new CustomFunction<>() {
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        };

        System.out.println("Sucessor - Anonima");
        System.out.println(sucessor.apply(10));
        System.out.println("----------------------------");

        CustomFunction<Integer, Integer> sucessorLambda = integer -> integer + 1;

        System.out.println("Sucessor - Lambda");
        System.out.println(sucessorLambda.apply(10));
        System.out.println("----------------------------");


    }

}
