package t1;

public class ReturnOrFinally {
    public static void main(String[] args) {
        int x = f1();

        System.out.println("x = "+x);
    }

    private static int f1() {
        try {
            return 0;
        } finally {
            System.out.println("in finally");
        }
    }
}
