package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public class Subtract extends BinaryOperation {

    public Subtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public <T extends Number> T calculate(T first, T second, AbstractMaths<T> maths) {
        return maths.subtract(first, second);
    }
}
