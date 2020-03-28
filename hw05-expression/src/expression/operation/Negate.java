package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public class Negate extends UnaryOperation {

    public Negate(CommonExpression first) {
        super(first);
    }

    @Override
    public <T extends Number> T calculate(T first, AbstractMaths<T> maths) {
        return maths.negate(first);
    }
}
