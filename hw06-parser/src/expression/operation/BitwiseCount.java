package expression.operation;

import expression.CommonExpression;
import expression.maths.AbstractMaths;

public class BitwiseCount extends UnaryOperation {

    public BitwiseCount(CommonExpression first) {
        super(first);
    }

    @Override
    public <T extends Number> T calculate(T first, AbstractMaths<T> maths) {
        return maths.bitwiseCount(first);
    }
}
