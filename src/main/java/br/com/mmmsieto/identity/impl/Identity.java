package br.com.mmmsieto.identity.impl;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Identity {

    public static String encripta(String mensagem, Function<String, String> algoritmoAdicional) {
        String mensagemEncriptada = mensagem.replaceAll("a", "4");
        String mensagemSuperEncriptada = algoritmoAdicional.apply(mensagemEncriptada);
        return mensagemSuperEncriptada;
    }

    public static void main(String[] args) {

        System.out.println(encripta("ola", s -> s.replaceAll("o", "0")));
        System.out.println(encripta("ola", s -> s));

        Map<Integer, Long> ocorrenciaDenNotas = Stream.of(10, 10, 0, 8, 8, 8)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        System.out.println(ocorrenciaDenNotas);

        Stream<Integer> integerStream =
                Stream.of(Stream.of(1, 2, 3), Stream.of(4, 5, 6), Stream.of(7, 8, 9))
                .flatMap(s -> s);

        integerStream.forEach(System.out::println);

    }

}
