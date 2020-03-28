package expression.maths;

import expression.exception.IncorrectConstException;
import expression.exception.ParsingException;

public class ShortMaths implements AbstractMaths<Short> {

    private static final ShortMaths INSTANCE = new ShortMaths();

    public static AbstractMaths<Short> getInstance() {
        return INSTANCE;
    }

    @Override
    public Short add(Short first, Short second) {
        return (short) (first + second);
    }

    @Override
    public Short subtract(Short first, Short second) {
        return (short) (first - second);
    }

    @Override
    public Short multiply(Short first, Short second) {
        return (short) (first * second);
    }

    @Override
    public Short divide(Short first, Short second) {
        return (short) (first / second);
    }

    @Override
    public Short negate(Short first) {
        return (short) (-first);
    }

    @Override
    public Short bitwiseAnd(Short first, Short second) {
        return (short) (first & second);
    }

    @Override
    public Short bitwiseOr(Short first, Short second) {
        return (short) (first | second);
    }

    @Override
    public Short bitwiseXor(Short first, Short second) {
        return (short) (first ^ second);
    }

    @Override
    public Short bitwiseNot(Short first) {
        return (short) (~first);
    }

    @Override
    public Short bitwiseCount(Short first) {
        short result = 0;
        while (first != 0) {
            result++;
            first = (short) (first & (first - 1));
        }
        return result;
    }

    @Override
    public Short logarithm(Short first, Short second) {
        short result = 0;
        while(first >= second) {
            first = (short) (first / second);
            result++;
        }
        return result;
    }

    @Override
    public Short power(Short first, Short second) {
        short result = 1;
        short n = 0;
        while (n != second) {
            if (n == 0) {
                result *= first;
                n++;
                continue;
            }
            if (n * 2 <= second) {
                result *= result;
                n <<= 1;
            } else {
                result *= first;
                n++;
            }
        }
        return result;
    }

    @Override
    public Short min(Short first, Short second) {
        short result;
        if (first < second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Short max(Short first, Short second) {
        short result;
        if (first > second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Short fromNumber(Number number) {
        return number.shortValue();
    }

    @Override
    public Short parseNumber(String tmp) throws ParsingException {
        try {
            return Short.parseShort(tmp);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(tmp);
        }
    }

    @Override
    public Short getNull() {
        return 0;
    }
}
