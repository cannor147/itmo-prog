package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public abstract class BinaryOperation implements CommonExpression {

    CommonExpression first, second;

    public BinaryOperation(CommonExpression first, CommonExpression second) {
        this.first = first;
        this.second = second;
    }

    public abstract <T extends Number> T calculate(T first, T second, AbstractMaths<T> maths);

    @Override
    public <T extends Number> T evaluate(T x, AbstractMaths<T> maths) {
        return calculate(first.evaluate(x, maths), second.evaluate(x, maths), maths);
    }

    @Override
    public <T extends Number> T evaluate(T x, T y, T z, AbstractMaths<T> maths) {
        return calculate(first.evaluate(x, y, z, maths), second.evaluate(x, y, z, maths), maths);
    }
}
