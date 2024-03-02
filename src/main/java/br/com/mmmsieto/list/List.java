package br.com.mmmsieto.list;

import br.com.mmmsieto.trampoline.RecursiveTailCall;

public abstract class List<T> {

    public abstract T head();
    public abstract List<T> tail();
    public abstract boolean isEmpty();
    public abstract List<T> setHead(T t);

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

    }


}
