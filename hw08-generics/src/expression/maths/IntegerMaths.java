package expression.maths;

import expression.exception.IncorrectConstException;
import expression.exception.ParsingException;

public class IntegerMaths implements AbstractMaths<Integer> {

    private static final IntegerMaths INSTANCE = new IntegerMaths();

    public static AbstractMaths<Integer> getInstance() {
        return INSTANCE;
    }

    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer subtract(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer divide(Integer first, Integer second) {
        return first / second;
    }

    @Override
    public Integer negate(Integer first) {
        return -first;
    }

    @Override
    public Integer bitwiseAnd(Integer first, Integer second) {
        return first & second;
    }

    @Override
    public Integer bitwiseOr(Integer first, Integer second) {
        return first | second;
    }

    @Override
    public Integer bitwiseXor(Integer first, Integer second) {
        return first ^ second;
    }

    @Override
    public Integer bitwiseNot(Integer first) {
        return ~first;
    }

    @Override
    public Integer bitwiseCount(Integer first) {
        int result = 0;
        while (first != 0) {
            result++;
            first = first & (first - 1);
        }
        return result;
    }

    @Override
    public Integer logarithm(Integer first, Integer second) {
        int result = 0;
        while(first >= second) {
            first /= second;
            result++;
        }
        return result;
    }

    @Override
    public Integer power(Integer first, Integer second) {
        int result = 1;
        int n = 0;
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
    public Integer min(Integer first, Integer second) {
        int result;
        if (first < second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Integer max(Integer first, Integer second) {
        int result;
        if (first > second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Integer fromNumber(Number number) {
        return number.intValue();
    }

    @Override
    public Integer parseNumber(String tmp) throws ParsingException {
        try {
            return Integer.parseInt(tmp);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(tmp);
        }
    }

    @Override
    public Integer getNull() {
        return 0;
    }
}
