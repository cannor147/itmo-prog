package expression;

import expression.maths.AbstractMaths;

public class Const implements CommonExpression {

    private Number mValue;

    public Const(Number value) {
        this.mValue = value;
    }

    @Override
    public <T extends Number> T evaluate(T x, AbstractMaths<T> maths) {
        return maths.fromNumber(mValue);
    }

    @Override
    public <T extends Number> T evaluate(T x, T y, T z, AbstractMaths<T> maths) {
        return maths.fromNumber(mValue);
    }
}
