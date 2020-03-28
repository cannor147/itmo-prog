package expression.maths;

import expression.exception.ParsingException;

public interface AbstractMaths<T extends Number> {

//    Mode whatMaths();

    T add(final T first, final T second);
    T subtract(final T first, final T second);
    T multiply(final T first, final T second);
    T divide(final T first, final T second);
    T negate(final T first);

    T bitwiseAnd(final T first, final T second);
    T bitwiseOr(final T first, final T second);
    T bitwiseXor(final T first, final T second);
    T bitwiseNot(final T first);
    T bitwiseCount(final T first);

    T logarithm(final T first, final T second);
    T power(final T first, final T second);

    T min(final T first, final T second);
    T max(final T first, final T second);

    T fromNumber(final Number number);
    T parseNumber(final String tmp) throws ParsingException;
    T getNull();

    default boolean isNull(final T first) {
        return first.equals(getNull());
    }
}
