package br.com.mmmsieto.cachememoization;

import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
public interface Function<T, R> {
    static Function<String, String> compose(
            Function<String, String> f,
            Function<String, String> g) {
        return s -> f.apply(g.apply(s));
    }

    static Function<Double, String> compose2(
            Function<Integer, String> f,
            Function<Double, Integer> g) {
        return d -> f.apply(g.apply(d));
    }

    static <T, R, V> Function<V, R> composegeneric(
            Function<T, R> f,
            Function<V, T> g) {
        return d -> f.apply(g.apply(d));
    }

    R apply(T t);

    static <T> Function<T, T> identity() {
        return e -> e;
    }

    default <V> Function<V, R> compose(Function<V, T> g) {
        return v -> this.apply(g.apply(v));
    }

    default Function<T, R> memoize() {
        ConcurrentHashMap<T, R> cache = new ConcurrentHashMap<>();
        return t -> cache.computeIfAbsent(t, k -> apply(k));
    }

}
