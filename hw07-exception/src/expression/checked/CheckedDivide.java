package expression.checked;

import expression.CommonExpression;
import expression.exception.OverflowException;
import expression.maths.CheckedIntegerMaths;
import expression.operation.Divide;

public class CheckedDivide extends Divide implements Checked {

    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
