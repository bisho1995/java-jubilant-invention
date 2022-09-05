package concepts.functionalinterface;

public class Consumer {
    public static void main(String[] args) {
        java.util.function.Consumer<StringBuffer> consumer = sb-> {
            sb.append("_changed");
        };

        StringBuffer sb1 = new StringBuffer("sb1");
        consumer.accept(sb1);

        System.out.println(sb1.toString().endsWith("_changed"));
    }
}
