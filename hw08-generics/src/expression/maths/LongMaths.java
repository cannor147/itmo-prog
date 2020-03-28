package expression.maths;

import expression.exception.IncorrectConstException;
import expression.exception.ParsingException;

public class LongMaths implements AbstractMaths<Long> {

    private static final LongMaths INSTANCE = new LongMaths();

    public static AbstractMaths<Long> getInstance() {
        return INSTANCE;
    }

    @Override
    public Long add(Long first, Long second) {
        return first + second;
    }

    @Override
    public Long subtract(Long first, Long second) {
        return first - second;
    }

    @Override
    public Long multiply(Long first, Long second) {
        return first * second;
    }

    @Override
    public Long divide(Long first, Long second) {
        return first / second;
    }

    @Override
    public Long negate(Long first) {
        return -first;
    }

    @Override
    public Long bitwiseAnd(Long first, Long second) {
        return first & second;
    }

    @Override
    public Long bitwiseOr(Long first, Long second) {
        return first | second;
    }

    @Override
    public Long bitwiseXor(Long first, Long second) {
        return first ^ second;
    }

    @Override
    public Long bitwiseNot(Long first) {
        return ~first;
    }

    @Override
    public Long bitwiseCount(Long first) {
        long result = 0;
        while (first != 0) {
            result++;
            first = first & (first - 1);
        }
        return result;
    }

    @Override
    public Long logarithm(Long first, Long second) {
        long result = 0;
        while(first >= second) {
            first /= second;
            result++;
        }
        return result;
    }

    @Override
    public Long power(Long first, Long second) {
        long result = 1;
        long n = 0;
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
    public Long min(Long first, Long second) {
        long result;
        if (first < second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Long max(Long first, Long second) {
        long result;
        if (first > second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Long fromNumber(Number number) {
        return number.longValue();
    }

    @Override
    public Long parseNumber(String tmp) throws ParsingException {
        try {
            return Long.parseLong(tmp);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(tmp);
        }
    }

    @Override
    public Long getNull() {
        return 0L;
    }
}
