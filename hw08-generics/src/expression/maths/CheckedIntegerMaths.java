package expression.maths;

import expression.Const;
import expression.checked.CheckedMultiply;
import expression.exception.OverflowException;

public class CheckedIntegerMaths extends IntegerMaths {

    private static final CheckedIntegerMaths INSTANCE = new CheckedIntegerMaths();

    public static CheckedIntegerMaths getInstance() {
        return INSTANCE;
    }

    @Override
    public Integer add(Integer first, Integer second) {
        if ((first > 0) && (Integer.MAX_VALUE - first < second) ||
                (first < 0) && (Integer.MIN_VALUE - first > second)) {
            throw new OverflowException();
        }

        return super.add(first, second);
    }

    @Override
    public Integer subtract(Integer first, Integer second) {
        if (((first >= 0) && (second < 0) && (first - Integer.MAX_VALUE > second)) ||
                ((first <= 0) && (second > 0) && (Integer.MIN_VALUE - first > -second))) {
            throw new OverflowException();
        }

        return super.subtract(first, second);
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        if (((first > 0) && (second > 0) && (Integer.MAX_VALUE / first < second)) ||
                ((first > 0) && (second < 0) && (Integer.MIN_VALUE / first > second)) ||
                ((first < 0) && (second > 0) && (Integer.MIN_VALUE / second > first)) ||
                ((first < 0) && (second < 0) && (Integer.MAX_VALUE / first > second))) {
            throw new OverflowException();
        }

        return super.multiply(first, second);
    }

    @Override
    public Integer divide(Integer first, Integer second) {
        if (second == 0) {
            throw new ArithmeticException("division by zero");
        }
        if ((first == Integer.MIN_VALUE) && (second == -1)) {
            throw new OverflowException();
        }

        return super.divide(first, second);
    }

    @Override
    public Integer negate(Integer first) {
        if (first == Integer.MIN_VALUE) {
            throw new OverflowException();
        }

        return super.negate(first);
    }

    @Override
    public Integer logarithm(Integer first, Integer second) {
        if (first <= 0) {
            throw new ArithmeticException("logarithm from non-positive number");
        }
        if ((second <= 0) || (second == 1)) {
            throw new ArithmeticException("incorrect base of logarithm");
        }

        return super.logarithm(first, second);
    }

    @Override
    public Integer power(Integer first, Integer second) {
        if ((first == 0) && (second == 0)) {
            throw new ArithmeticException("zero to zero powering");
        }
        if (second < 0) {
            throw new ArithmeticException("powering by negative exponent");
        }

        int result = 1;
        if (first == 0) {
            result = 0;
        } else if (first == -1) {
            if (second % 2 == 1) {
                result = -1;
            }
        } else if (first != 1) {
            int n = 0;
            while (n != second) {
                if (n == 0) {
                    result = multiply(first, result);
                    n++;
                    continue;
                }
                if (n * 2 <= second) {
                    result = multiply(result, result);
                    n <<= 1;
                } else {
                    result = multiply(first, result);
                    n++;
                }
            }
        }
        return result;
    }
}
