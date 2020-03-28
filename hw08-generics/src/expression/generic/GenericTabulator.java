package expression.generic;

import expression.CommonExpression;
import expression.exception.ParsingException;
import expression.parser.ExpressionParser;
import expression.maths.*;

public class GenericTabulator implements Tabulator {

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Mode m = Mode.toMode(mode);
        AbstractMaths<?> maths = getMaths(m);
        return makeTable(maths, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Number> Object[][][] makeTable(AbstractMaths<T> maths, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser parser = new ExpressionParser(maths);
        CommonExpression myExpression;
        try {
            myExpression = parser.parse(expression);
        } catch (ParsingException e) {
            return res;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        T x = maths.fromNumber(i);
                        T y = maths.fromNumber(j);
                        T z = maths.fromNumber(k);
                        res[i - x1][j - y1][k - z1] = myExpression.evaluate(x, y, z, maths);
                    } catch (ArithmeticException e) {
                        res[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return res;
    }

    public static AbstractMaths<?> getMaths(Mode mode) {
        switch (mode) {
            case I:
                return new CheckedIntegerMaths();
            case D:
                return new DoubleMaths();
            case BI:
                return new BigIntegerMaths();
            case U:
                return new IntegerMaths();
            case L:
                return new LongMaths();
            case S:
                return new ShortMaths();
            default:
                throw new IllegalArgumentException("incorrect mode");
        }
    }
}