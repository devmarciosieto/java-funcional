package br.com.mmmsieto.intro;

@FunctionalInterface
public interface CustomFunction<T, R> {

    R apply(T t);
}
