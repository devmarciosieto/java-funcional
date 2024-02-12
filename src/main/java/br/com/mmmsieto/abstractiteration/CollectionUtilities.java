package br.com.mmmsieto.abstractiteration;

import br.com.mmmsieto.composition.Function;

import java.util.ArrayList;
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
