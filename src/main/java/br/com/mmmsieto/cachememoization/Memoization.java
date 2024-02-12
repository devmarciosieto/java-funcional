package br.com.mmmsieto.cachememoization;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Memoization {

    public static void main(String[] args) {

        Function<List<Integer>, Double> calculatesWinningProbability = numero -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (numero.containsAll(List.of(1, 5, 9))) {
                return 99D;
            } else if (numero.containsAll(List.of(2, 4, 6))) {
                return 90D;
            } else {
                return 30D;
            }
        };

        long start = System.currentTimeMillis();
        System.out.println("Probability of winning: " + calculatesWinningProbability.apply(List.of(1, 5, 9)));
        System.out.println("Processeing Time: " + (System.currentTimeMillis() - start));

        System.out.println("-----------------------------------");

        long start2 = System.currentTimeMillis();
        System.out.println("Probability of winning 2: " + calculatesWinningProbability.apply(List.of(1, 5, 9)));
        System.out.println("Processeing Time: " + (System.currentTimeMillis() - start2));

        System.out.println("************************ Cache ************************************");

        ConcurrentHashMap<List<Integer>, Double> cache = new ConcurrentHashMap<>();

        Function<List<Integer>, Double> calculatesWinningProbabilityCache = numero -> {

            return cache.computeIfAbsent(numero, k -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (numero.containsAll(List.of(1, 5, 9))) {
                    return 99D;
                } else if (numero.containsAll(List.of(2, 4, 6))) {
                    return 90D;
                } else {
                    return 30D;
                }
            });
        };

        long start3 = System.currentTimeMillis();
        System.out.println("Probability of winning: " + calculatesWinningProbabilityCache.apply(List.of(1, 5, 9)));
        System.out.println("Processeing Time: " + (System.currentTimeMillis() - start3));

        System.out.println("-----------------------------------");

        long start4 = System.currentTimeMillis();
        System.out.println("Probability of winning: " + calculatesWinningProbabilityCache.apply(List.of(1, 5, 9)));
        System.out.println("Processeing Time: " + (System.currentTimeMillis() - start4));

        System.out.println("************************ Cache Memoized ************************************");

        Function<List<Integer>, Double> calculatesWinningProbabilityMemoize =
                calculatesWinningProbability.memoize();

        long start5 = System.currentTimeMillis();
        System.out.println("Probability of winning memoize: " + calculatesWinningProbabilityMemoize.apply(List.of(1, 5, 9)));
        System.out.println("Processeing Time memoize: " + (System.currentTimeMillis() - start5));

        System.out.println("-----------------------------------");

        long start6 = System.currentTimeMillis();
        System.out.println("Probability of winning memoize: " + calculatesWinningProbabilityMemoize.apply(List.of(1, 5, 9)));
        System.out.println("Processeing Time memoize: " + (System.currentTimeMillis() - start6));

        System.out.println("-----------------------------------");

        long start7 = System.currentTimeMillis();
        System.out.println("Probability of winning memoize: " + calculatesWinningProbabilityMemoize.apply(List.of(2, 4, 6)));
        System.out.println("Processeing Time memoize: " + (System.currentTimeMillis() - start7));


    }

}
