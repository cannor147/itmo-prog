package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public class Logarithm extends BinaryOperation {

    public Logarithm(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public <T extends Number> T calculate(T first, T second, AbstractMaths<T> maths) {
        return maths.logarithm(first, second);
    }
}
