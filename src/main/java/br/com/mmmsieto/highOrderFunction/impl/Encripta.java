package br.com.mmmsieto.highOrderFunction.impl;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Encripta {

    public static void main(String[] args) {

        // Anonymous class
        BiFunction<String, Function<String, String>, String> encriptadorAnonymous = new BiFunction<>() {

            @Override
            public String apply(String t, Function<String, String> s) {
                StringBuilder textoEncriptado = new StringBuilder();
                for (int i = 0; i < t.length(); i++) {
                    textoEncriptado.append((char) (t.charAt(i) + s.apply(t).length()));
                }
                return textoEncriptado.toString();
            }
        };

        // Lambda
        BiFunction<String, Function<String, String>, String> encriptadorLambda = (t, s) -> {
            StringBuilder textoEncriptado = new StringBuilder();
            for (int i = 0; i < t.length(); i++) {
                textoEncriptado.append((char) (t.charAt(i) + s.apply(t).length()));
            }
            return textoEncriptado.toString();
        };

        String textoEncriptadoAnonymous = encripta(encriptadorAnonymous);
        String textoEncriptadoLambda= encripta(encriptadorLambda);

        System.out.println("Anonymous --> " + textoEncriptadoAnonymous);
        System.out.println("Lambda --> " + textoEncriptadoLambda);
    }

    private static String encripta(BiFunction<String, Function<String,String>, String> encriptador) {
        return encriptador.apply("Texto a ser encriptado", t -> t.replaceAll(" ", "sfsdfdsds"));

    }

}
