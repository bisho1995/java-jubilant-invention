package concepts.functionalinterface;

import java.util.Random;

public class Supplier {
    public static void main(String[] args) {
        Random random = new Random();
        java.util.function.Supplier<Integer> nextInt = ()-> random.nextInt();

        System.out.println(nextInt.get());
    }
}
