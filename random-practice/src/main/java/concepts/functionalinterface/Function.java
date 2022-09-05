package concepts.functionalinterface;

public class Function {
    public static void main(String[] args) {
        java.util.function.Function<Integer, String> fn = x->"x is " + x;

        System.out.println(fn.apply(22));
    }
}
