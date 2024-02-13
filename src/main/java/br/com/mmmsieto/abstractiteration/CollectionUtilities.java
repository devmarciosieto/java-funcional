package br.com.mmmsieto.abstractiteration;

import br.com.mmmsieto.composition.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionUtilities {

    public static void main(String[] args) {

        List<Integer> points = List.of(100, 200, 300);
        //points.stream().map(p -> p * 2);
        List<Integer> newPoints = calculatesPoints(points);
       // newPoints.add(1);
        System.out.println(newPoints);

        List<Integer> newPoints2 = myMap(points, doublePoints);
        System.out.println("************");
        System.out.println(newPoints2);

        System.out.println("************");

        Integer[] numbers = {1, 2, 3};
        List<Integer> listNumbers = list(numbers);

        System.out.println(listNumbers);

        numbers[2] = 0;

        System.out.println(listNumbers);

    }

    static <T> List<T> list() {
        return Collections.emptyList();
    }

    static <T> List<T> list(T t) {
        return Collections.singletonList(t);
    }

    static <T> List<T> list(List<T> list) {
        return Collections.unmodifiableList(new ArrayList<>(list));
    }

    static <T> List<T> list(T ... t) {
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
    }

    static <T> T head(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalStateException("Calling head on an empty list");
        }

        return list.get(0);
    }

    static <T> List<T> tail(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalStateException("Calling tail on an empty list");
        }

        List<T> newList = copy(list);
        newList.remove(0);
        return Collections.unmodifiableList(newList);
    }

    static <T> List<T> append(List<T> list, T t) {
        List<T> newList = copy(list);
        newList.add(t);
        return Collections.unmodifiableList(newList);
    }

    private static <T> List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    static <T, U> List<U> myMap(List<T> l, Function<T, U> f) {
        List<U> newPoints = new ArrayList<>();
        for (T element : l) {
            newPoints.add(f.apply(element));
        }
        return Collections.unmodifiableList(newPoints);
    }

    static List<Integer> calculatesPoints(List<Integer> points) {
        List<Integer> newPoints = new ArrayList<>();
        for (Integer p : points) {
            newPoints.add(doublePoints.apply(p));
        }
        return Collections.unmodifiableList(newPoints);
    }

    static Function<Integer, Integer> doublePoints = p -> p * 2;

}
