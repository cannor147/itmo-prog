package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public class BitwiseNot extends UnaryOperation {

    public BitwiseNot(CommonExpression first) {
        super(first);
    }

    @Override
    public <T extends Number> T calculate(T first, AbstractMaths<T> maths) {
        return maths.bitwiseNot(first);
    }
}
