package expression.maths;

import expression.exception.IncorrectConstException;
import expression.exception.ParsingException;

import java.math.BigInteger;

public class BigIntegerMaths implements AbstractMaths<BigInteger> {

    private static final BigIntegerMaths INSTANCE = new BigIntegerMaths();

    public static AbstractMaths<BigInteger> getInstance() {
        return INSTANCE;
    }

    @Override
    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    @Override
    public BigInteger subtract(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    @Override
    public BigInteger multiply(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    @Override
    public BigInteger divide(BigInteger first, BigInteger second) {
        return first.divide(second);
    }

    @Override
    public BigInteger negate(BigInteger first) {
        return first.negate();
    }

    @Override
    public BigInteger bitwiseAnd(BigInteger first, BigInteger second) {
        return first.and(second);
    }

    @Override
    public BigInteger bitwiseOr(BigInteger first, BigInteger second) {
        return first.or(second);
    }

    @Override
    public BigInteger bitwiseXor(BigInteger first, BigInteger second) {
        return first.xor(second);
    }

    @Override
    public BigInteger bitwiseNot(BigInteger first) {
        return first.not();
    }

    @Override
    public BigInteger bitwiseCount(BigInteger first) {
        return BigInteger.valueOf(first.bitCount());
    }

    @Override
    public BigInteger logarithm(BigInteger first, BigInteger second) {
        BigInteger result = BigInteger.ZERO;
        while(first.compareTo(second) >= 0) {
            first = first.divide(second);
            result = result.add(BigInteger.ONE);
        }
        return result;
    }

    @Override
    public BigInteger power(BigInteger first, BigInteger second) {
        BigInteger result = BigInteger.ONE;
        BigInteger n = BigInteger.ZERO;
        while (!n.equals(second)) {
            if (n.equals(BigInteger.ZERO)) {
                result = multiply(result, first);
                n = add(n, BigInteger.ONE);
                continue;
            }
            if (multiply(n, BigInteger.TWO).compareTo(second) <= 0) {
                result = multiply(result, result);
                n = n.shiftLeft(1);
            } else {
                result = multiply(result, first);
                n = add(n, BigInteger.ONE);
            }
        }
        return result;
    }

    @Override
    public BigInteger min(BigInteger first, BigInteger second) {
        return first.min(second);
    }

    @Override
    public BigInteger max(BigInteger first, BigInteger second) {
        return first.max(second);
    }

    @Override
    public BigInteger fromNumber(Number number) {
        return BigInteger.valueOf(number.longValue());
    }

    @Override
    public BigInteger parseNumber(String tmp) throws ParsingException {
        try {
            return new BigInteger(tmp);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(tmp);
        }
    }

    @Override
    public BigInteger getNull() {
        return BigInteger.ZERO;
    }
}
