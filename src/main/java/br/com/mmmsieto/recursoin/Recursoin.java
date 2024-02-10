package br.com.mmmsieto.recursoin;

public class Recursoin {

    public static void main(String[] args) {

        System.out.println("*********** repeatProcedural ***************************");
        System.out.println(repeatProcedural("a", 150_000));
        System.out.println("**************************************");

        System.out.println("************ repeatRecursive ***************************");
        System.out.println(repeatRecursive("a", 150_000));
        System.out.println("**************************************");


        System.out.println(countsN("N dlfs NsdfoNfosNfowew Neos"));
        System.out.println("**************************************");
        System.out.println(sum(2));

    }

    static int sum(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sum(n - 1);
        }
    }

    static int countsN(String s) {
        if (s.isEmpty()) {
            return 0;
        } else if (s.charAt(0) == 'N') {
            return 1 + countsN(s.substring(1));
        } else {
            return countsN(s.substring(1));
        }
    }

    static String repeatRecursive(String s, int n) {
        if (n == 1) return s;
        else return s + repeatRecursive(s, n - 1);
    }

    static String repeatProcedural(String s, int n) {
        String result = "";
        int counter = 0;

        while (counter < n) {
            result = result + s;
            counter++;
        }
        return result;
    }

}
