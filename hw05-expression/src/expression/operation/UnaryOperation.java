package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public abstract class UnaryOperation implements CommonExpression {

    CommonExpression first;

    public UnaryOperation(CommonExpression first) {
        this.first = first;
    }

    public abstract <T extends Number> T calculate(T first, AbstractMaths<T> maths);

    @Override
    public <T extends Number> T evaluate(T x, AbstractMaths<T> maths) {
        return calculate(first.evaluate(x, maths), maths);
    }

    @Override
    public <T extends Number> T evaluate(T x, T y, T z, AbstractMaths<T> maths) {
        return calculate(first.evaluate(x, y, z, maths), maths);
    }
}
