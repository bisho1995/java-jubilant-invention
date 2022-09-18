package jfokus;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

public class PhoneNumber implements Formattable, Comparable<PhoneNumber> {

    /**
     * Selectively applying setters/getters to 2 fields in this class
     * Note: since the fields are marked as final, setter won't work
     * here.
     */
    @Getter
    @Setter
    private final int areaCode,number;
    private final int hash;

    private static final Comparator<PhoneNumber> PHONE_NUMBER_COMPARATOR = Comparator
            .comparingInt((PhoneNumber p) -> p.areaCode)
            .thenComparingInt(p -> p.number);

    private PhoneNumber(int areaCode, int number) {
        this.areaCode = areaCode;
        this.number = number;

        this.hash = Objects.hash(areaCode, number);
    }

    /**
     * Created a factory method of the constructor. Option return will show
     * you the option to extract this to a factory method.
     *
     * @param areaCode
     * @param number
     * @return
     */
    public static PhoneNumber of(int areaCode, int number) {
        return new PhoneNumber(areaCode, number);
    }

    @Override
    public String toString() {
        /**
         * This shows guava function to generate toString implementation
         */
        return MoreObjects.toStringHelper(this)
                .add("areaCode", areaCode)
                .add("number",number)
                .toString();
    }


    /**
     * Formats the object using the provided {@link Formatter formatter}.
     *
     * @param formatter The {@link Formatter formatter}.  Implementing classes may call
     *                  {@link Formatter#out() formatter.out()} or {@link
     *                  Formatter#locale() formatter.locale()} to obtain the {@link
     *                  Appendable} or {@link Locale} used by this
     *                  {@code formatter} respectively.
     * @param flags     The flags modify the output format.  The value is interpreted as
     *                  a bitmask.  Any combination of the following flags may be set:
     *                  {@link FormattableFlags#LEFT_JUSTIFY}, {@link
     *                  FormattableFlags#UPPERCASE}, and {@link
     *                  FormattableFlags#ALTERNATE}.  If no flags are set, the default
     *                  formatting of the implementing class will apply.
     * @param width     The minimum number of characters to be written to the output.
     *                  If the length of the converted value is less than the
     *                  {@code width} then the output will be padded by
     *                  <code>'&nbsp;&nbsp;'</code> until the total number of characters
     *                  equals width.  The padding is at the beginning by default.  If
     *                  the {@link FormattableFlags#LEFT_JUSTIFY} flag is set then the
     *                  padding will be at the end.  If {@code width} is {@code -1}
     *                  then there is no minimum.
     * @param precision The maximum number of characters to be written to the output.
     *                  The precision is applied before the width, thus the output will
     *                  be truncated to {@code precision} characters even if the
     *                  {@code width} is greater than the {@code precision}.  If
     *                  {@code precision} is {@code -1} then there is no explicit
     *                  limit on the number of characters.
     * @throws IllegalFormatException If any of the parameters are invalid.  For specification of all
     *                                possible formatting errors, see the <a
     *                                href="../util/Formatter.html#detail">Details</a> section of the
     *                                formatter class specification.
     */
    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%d-%d", areaCode, number);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(PhoneNumber o) {
        /**
         * Guava way of writing comparators
         */
//        return ComparisonChain
//                .start()
//                .compare(areaCode, o.areaCode)
//                .compare(number, o.number)
//                .result();

        /**
         * This is the java8 way of writing comparators
         */
        return PHONE_NUMBER_COMPARATOR
                .compare(this,o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && number == that.number;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public static void main(String[] args) {
        PhoneNumber phoneNumber = PhoneNumber.of(100, 2000);

//        phoneNumber.set
    }
}
