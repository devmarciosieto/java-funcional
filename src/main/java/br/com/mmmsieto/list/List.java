package br.com.mmmsieto.list;

import br.com.mmmsieto.composition.Function;
import br.com.mmmsieto.trampoline.RecursiveTailCall;

public abstract class List<T> {

    public abstract T head();
    public abstract List<T> tail();
    public abstract boolean isEmpty();
    public abstract List<T> setHead(T t);
    public abstract List<T> drop(Integer n);
    public abstract List<T> dropWhile(Function<T, Boolean> f);
    public abstract List<T> init();

    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();

    private List(){}

    public static <T> List<T> list() {
        return NIL;
    }

    public List<T> cons(T t) {
        return new Cons<>(t, this);
    }

    public static <T> List<T> list(T ...t) {
        List<T> r = list();
        for (int i = t.length - 1; i >= 0; i--) {
            r = new Cons<>(t[i], r);
        }
        return r;
    }

//    public static <T> List<T> reverse(List<T> acc, List<T> l) {
//        return l.isEmpty() ? acc : reverse(new Cons<>(l.head(), acc), l.tail());
//    }

    public List<T> reverse() {
        return reverse_(list(), this).get();
    }

    private RecursiveTailCall<List<T>> reverse_(List<T> acc, List<T> l) {
        return l.isEmpty() ? RecursiveTailCall.terminal(acc) : RecursiveTailCall.nonTerminalCall(
                () -> reverse_(new Cons<>(l.head(), acc), l.tail()));
    }

    private static class Cons<T> extends List<T> {
        private final T head;
        private final List<T> tail;


        private Cons(T head, List<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public List<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<T> setHead(T t) {
            return new Cons<>(t, this.tail());
            // [1, 2].setHead(0)
            // new Cons(0, [2])  -> [0, 2]
        }

//        @Override
//        public List<T> drop(Integer n) {
//            return n <= 0
//                    ? this
//                    : tail().drop(n - 1);
//        }

        @Override
        public List<T> drop(Integer n) {
            return drop_(this, n).get();
        }

        @Override
        public List<T> dropWhile(Function<T, Boolean> f) {
            return dropWhile_(this, f).get();
        }

        @Override
        public List<T> init() {
            return reverse().tail().reverse();
        }

        private RecursiveTailCall<List<T>> dropWhile_(List<T> acc, Function<T, Boolean> f) {
            return !acc.isEmpty() && f.apply(acc.head())
                    ? RecursiveTailCall.nonTerminalCall(() -> dropWhile_(acc.tail(), f))
                    : RecursiveTailCall.terminal(acc);
        }

        private RecursiveTailCall<List<T>> drop_(List<T> acc, Integer n) {
            return n <= 0 || acc.isEmpty()
                    ? RecursiveTailCall.terminal(acc)
                    : RecursiveTailCall.nonTerminalCall(() -> drop_(acc.tail(), n -1));
        }



//        @Override
//        public String toString() {
//            return head + ", " + tail.toString();
//        }

        @Override
        public String toString() {
            return "[" + toString_(new StringBuilder(), this).get() + "NIL]";
        }

        private RecursiveTailCall<StringBuilder> toString_(StringBuilder acc, List<T> list) {
            return list.isEmpty()
                    ? RecursiveTailCall.terminal(acc)
                    : RecursiveTailCall.nonTerminalCall(() -> toString_(acc.append(list.head()).append(", "), list.tail()));
        }

    }

    private static class Nil<T> extends List<T> {

        private Nil() {
        }
        @Override
        public T head() {
            throw new IllegalStateException("head method can not be called on an empty list");
        }

        @Override
        public List<T> tail() {
            throw new IllegalStateException("tail method can not be called on an empty list");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public List<T> setHead(T t) {
            throw new IllegalStateException("setHead method can not be called on an empty list");
        }

        @Override
        public List<T> drop(Integer n) {
            return this;
        }

        @Override
        public List<T> dropWhile(Function<T, Boolean> f) {
            return this;
        }

        @Override
        public List<T> init() {
            return this;
        }

        @Override
        public String toString() {
            return "[Nil]";
        }
    }

    public static void main(String[] args) {

        List<Integer> emptyList = list();
        // System.out.println(emptyList);

        List<Integer> oneElement = list(1);
        List<Integer> tweElement = list(1, 2); // <-- equal --> new Cons<Integer>(1, new Cons<Integer>(1, NIL));

        // [0, 1, 2]
        new Cons(0, tweElement); // [0, 1, 2]

        // cons(t)
        emptyList.cons(2); // [2]
        emptyList.cons(2).cons(1); // [1, 2]

        // setHead(t)
        System.out.println(tweElement.head()); // [1, 2].head() = 1
        System.out.println(tweElement.tail().head()); // [1, 2].tail() = [2] -> [2].head() = 2
        // System.out.println(tweElement.tail().tail().head()); // throw new IllegalStateException("head method can not be called on an empty list");

        System.out.println(tweElement.setHead(0).head());
        System.out.println(tweElement.head());

        System.out.println("-----------------------------------");
        System.out.println("toString");
        System.out.println(list());
        System.out.println(list(1));
        System.out.println(list(1, 2));

        System.out.println("-----------------------------------");
        System.out.println("drop");
        System.out.println(list(1, 2).drop(0));               // -> [1, 2, NIL]
        System.out.println(list(1, 2).drop(1));               // -> [2, NIL]
        System.out.println(list("a", "b", "c", "d").drop(3)); // -> ["d", NIL]

        System.out.println("-----------------------------------");
        System.out.println("dropWhile");
        System.out.println(list(1, 2, 3).dropWhile(e -> e < 3));    //-> [3, NIL]
        System.out.println(list(1, 2, 3).dropWhile(e -> e < 2));    //-> [2, 3, NIL]
        System.out.println(list(1, 2, 3).dropWhile(e -> e <= 2));   //-> [3, NIL]
        System.out.println(list(1, 2, 3).dropWhile(e -> e > 8));    //-> [1, 2, 3, NIL]

        System.out.println("-----------------------------------");
        System.out.println("reverse");
        System.out.println(list(1, 2, 3).reverse());

        System.out.println("-----------------------------------");
        System.out.println("init");
        System.out.println(list(1, 2, 3).init()); // [1, 2, NIL]
        System.out.println(list().init()); // [NIL]

    }

}
