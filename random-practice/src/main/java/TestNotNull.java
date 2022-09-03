import javax.annotation.Nullable;

public class TestNotNull {
    void doSomething(@Nullable String s) {
        System.out.println("s = " + s);
    }

    public static void main(String[] args) {
        TestNotNull testNotNull = new TestNotNull();

        testNotNull.doSomething("s");
    }
}
