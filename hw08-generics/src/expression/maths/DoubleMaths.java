package expression.maths;

import expression.exception.IncorrectConstException;
import expression.exception.ParsingException;

public class DoubleMaths implements AbstractMaths<Double> {

    private static final DoubleMaths INSTANCE = new DoubleMaths();

    public static AbstractMaths<Double> getInstance() {
        return INSTANCE;
    }

    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double subtract(Double first, Double second) {
        return first - second;
    }

    @Override
    public Double divide(Double first, Double second) {
        return first / second;
    }

    @Override
    public Double multiply(Double first, Double second) {
        return first * second;
    }

    @Override
    public Double negate(Double first) {
        return -first;
    }

    @Override
    public Double bitwiseAnd(Double first, Double second) {
        return null;
    }

    @Override
    public Double bitwiseOr(Double first, Double second) {
        return null;
    }

    @Override
    public Double bitwiseXor(Double first, Double second) {
        return null;
    }

    @Override
    public Double bitwiseNot(Double first) {
        return null;
    }

    @Override
    public Double bitwiseCount(Double first) {
        long tmp = Double.doubleToRawLongBits(first);
        double result = 0.0;
        while (tmp != 0) {
            result += 1.0;
            tmp = tmp & (tmp - 1);
        }
        return result;
    }

    @Override
    public Double logarithm(Double first, Double second) {
        return Math.log(first) / Math.log(second);
    }

    @Override
    public Double power(Double first, Double second) {
        return Math.pow(first, second);
    }

    @Override
    public Double min(Double first, Double second) {
        double result;
        if (first < second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Double max(Double first, Double second) {
        double result;
        if (first > second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    @Override
    public Double fromNumber(Number number) {
        return number.doubleValue();
    }

    @Override
    public Double parseNumber(final String tmp) throws ParsingException {
        try {
            return Double.parseDouble(tmp);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(tmp);
        }
    }

    @Override
    public Double getNull() {
        return 0.0;
    }
}