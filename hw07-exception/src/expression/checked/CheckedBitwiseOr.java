package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;
import expression.operation.BitwiseOr;

public class CheckedBitwiseOr extends BitwiseOr implements Checked {

    public CheckedBitwiseOr(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
