package expression.checked;

import expression.CommonExpression;
import expression.exception.OverflowException;
import expression.maths.CheckedIntegerMaths;
import expression.operation.Subtract;

public class CheckedSubtract extends Subtract implements Checked {

    public CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
