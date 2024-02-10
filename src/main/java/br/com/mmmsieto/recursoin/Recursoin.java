package br.com.mmmsieto.recursoin;

public class Recursoin {

    public static void main(String[] args) {

//        System.out.println("*********** repeatProcedural ***************************");
//        System.out.println(repeatProcedural("a", 150_000));
//        System.out.println("**************************************");

//        System.out.println("************ repeatRecursive ***************************");
//        System.out.println(repeatRecursive("a", 150_000));
//        System.out.println("**************************************");

//        System.out.println("************ repeatTailRecursive ***************************");
//        System.out.println(repeatTailRecursive("a", 3));
//        System.out.println("**************************************");


//        System.out.println(countsN("N dlfs NsdfoNfosNfowew Neos"));
//        System.out.println("**************************************");
//        System.out.println(sum(2));

//        System.out.println("**************************************");
//        System.out.println(countsNTailRecursive("N dlfs NsdfoNfosNfowew Neos"));
//        System.out.println("**************************************");

        System.out.println("**************** sum **********************");
        System.out.println(sum(5));

        System.out.println("*************** sumTailRecursive ***********************");
        System.out.println(sumTailRecursive(5));

    }

    static int sum(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sum(n - 1);
        }
    }

    static int sumTailRecursive(int n) {
        return sumTailRecursiveAssistant(n, 0);
    }

    static int sumTailRecursiveAssistant(int n, int acc) {
        if (n == 0) {
            return acc;
        } else {
            return sumTailRecursiveAssistant(n - 1, acc + n);
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

    static int countsNTailRecursive(String s) {
        return countsNTailRecursiveAssistant(s, 0);
    }

    static int countsNTailRecursiveAssistant(String s, int acc) {
        if (s.isEmpty()) {
            return acc;
        } else if (s.charAt(0) == 'N') {
            return countsNTailRecursiveAssistant(s.substring(1 ), acc + 1 );
        } else {
            return countsNTailRecursiveAssistant(s.substring(1), acc);
        }
    }

    static String repeatRecursive(String s, int n) {
        if (n == 1) return s;
        else return s + repeatRecursive(s, n - 1);
    }

    static String repeatTailRecursive(String s, int n) {
        return repeatTailRecursiveAssistant(s, n, "");
    }

    static String repeatTailRecursiveAssistant(String s, int n, String acc) {
        if (n == 1) return s + acc;
        else return repeatTailRecursiveAssistant(s, n - 1, s + acc);
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
