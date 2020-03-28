package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;
import expression.operation.BitwiseAnd;

public class CheckedBitwiseAnd extends BitwiseAnd implements Checked {

    public CheckedBitwiseAnd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
