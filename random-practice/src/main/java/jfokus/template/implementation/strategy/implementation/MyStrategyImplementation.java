package jfokus.template.implementation.strategy.implementation;

import java.util.function.Supplier;

public class MyStrategyImplementation {
    /**
     * In this example we are replacing the value of supplier
     * for different implementations
     * This is the new java8 way of doing things
     */
    private final Supplier<String> supplier;

    private MyStrategyImplementation(Supplier<String> supplier) {
        this.supplier = supplier;
    }

    public static MyStrategyImplementation of(Supplier<String> supplier) {
        return new MyStrategyImplementation(supplier);
    }

    public void doSomething(){
        System.out.println("abc");
        System.out.println(supplier.get());
        System.out.println("def");
    }

    public static void main(String[] args) {
        MyStrategyImplementation.of(()->"c").doSomething();
        MyStrategyImplementation.of(()->"d").doSomething();
    }
}
