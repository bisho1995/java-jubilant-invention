package jfokus.template.implementation.strategy.implementation;

/**
 * This is the pre java8 way of doing implementation
 */
public abstract class MyTemplateMethodImplementation {
    public void doSomething(){
        System.out.println("abc");
        System.out.println(getString());
        System.out.println("def");
    }

    protected abstract String getString();
}

class OneTemplateImplementation extends MyTemplateMethodImplementation {
    @Override
    protected String getString() {
        return "AA";
    }
}
