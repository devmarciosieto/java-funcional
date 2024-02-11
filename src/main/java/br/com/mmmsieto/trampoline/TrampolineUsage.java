package br.com.mmmsieto.trampoline;

public class TrampolineUsage {

    public static void main(String[] args) {

        RecursiveTailCall<String> a = repeatTailRecursiveAssistant("a", 3, "");
        RecursiveTailCall<String> b = a.next();
        RecursiveTailCall<String> c = b.next();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(c.get());

        System.out.println("*****************************************");

        System.out.println(repeatTailRecursive("e", 150_000));

    }

    static String repeatTailRecursive(String s, int n) {
        return repeatTailRecursiveAssistant(s, n, "").get();
    }

    static RecursiveTailCall<String> repeatTailRecursiveAssistant(String s, int n, String acc) {
        if (n == 1) return  RecursiveTailCall.terminal(s + acc);
        else return  RecursiveTailCall.nonTerminalCall(() -> repeatTailRecursiveAssistant(s, n - 1, s + acc));
    }

}
