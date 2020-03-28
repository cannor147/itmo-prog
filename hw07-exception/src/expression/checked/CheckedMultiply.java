package expression.checked;

import expression.CommonExpression;
import expression.exception.OverflowException;
import expression.maths.CheckedIntegerMaths;
import expression.operation.Multiply;

public class CheckedMultiply extends Multiply implements Checked {

    public CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
