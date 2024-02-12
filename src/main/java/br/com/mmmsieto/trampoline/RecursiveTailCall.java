package br.com.mmmsieto.trampoline;

import br.com.mmmsieto.laziness.Supplier;

public abstract class RecursiveTailCall<T> {

    public abstract RecursiveTailCall<T> next();

    public abstract T get();

    public abstract boolean isTerminal();

    private static class TerminalCall<T> extends RecursiveTailCall<T> {

        private final T result;

        private TerminalCall(T result) {
            this.result = result;
        }

        @Override
        public RecursiveTailCall<T> next() {
            throw new UnsupportedOperationException("You shouldn't call next in a terminal call");
        }

        @Override
        public T get() {
            return result;
        }

        @Override
        public boolean isTerminal() {
            return true;
        }

    }

    private static class NonTerminalCall<T> extends RecursiveTailCall<T> {

        private final Supplier<RecursiveTailCall<T>> nextCall;

        private NonTerminalCall(Supplier<RecursiveTailCall<T>> nextCall) {
            this.nextCall = nextCall;
        }

        @Override
        public RecursiveTailCall<T> next() {
            return nextCall.get();
        }

        @Override
        public T get() {
            RecursiveTailCall<T> tailCall = this;
            while (!tailCall.isTerminal()) {
                tailCall = tailCall.next();
            }

            return tailCall.get();
        }

        @Override
        public boolean isTerminal() {
            return false;
        }

    }

    public static <T> NonTerminalCall<T> nonTerminalCall(Supplier<RecursiveTailCall<T>> s) {
        return new NonTerminalCall<>(s);
    }

    public static <T> TerminalCall<T> terminal(T t) {
        return new TerminalCall<>(t);
    }

}
