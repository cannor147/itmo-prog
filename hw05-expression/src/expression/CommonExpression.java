package expression;

import expression.maths.AbstractMaths;
import expression.maths.DoubleMaths;
import expression.maths.IntegerMaths;

public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {

    <T extends Number> T evaluate(T x, AbstractMaths<T> maths);
    <T extends Number> T evaluate(T x, T y, T z, AbstractMaths<T> maths);

    @Override
    default double evaluate(double x) {
        return evaluate(x, DoubleMaths.getInstance());
    }

    @Override
    default int evaluate(int x) {
        return evaluate(x, IntegerMaths.getInstance());
    }

    @Override
    default int evaluate(int x, int y, int z) {
        return evaluate(x, y, z, IntegerMaths.getInstance());
    }
}
