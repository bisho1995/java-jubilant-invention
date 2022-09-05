package concepts.functionalinterface;

/**
 * Predicate.test always returns boolean, as in the input passed in
 * satisfies the condition or not.
 */
public class Predicate {
    public static void main(String[] args) {
        java.util.function.Predicate<String> isValidString = str-> str!=null && str.length()>0;

        System.out.println(isValidString.test("") == false);
        System.out.println(isValidString.test("sss") == true);
        System.out.println(isValidString.test(null) == false);
    }
}
