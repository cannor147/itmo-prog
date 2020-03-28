package expression.checked;

import expression.CommonExpression;
import expression.exception.OverflowException;
import expression.maths.CheckedIntegerMaths;
import expression.operation.Negate;

public class CheckedNegate extends Negate implements Checked {

    public CheckedNegate(CommonExpression first) {
        super(first);
    }
}
