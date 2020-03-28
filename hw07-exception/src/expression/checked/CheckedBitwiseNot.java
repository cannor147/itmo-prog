package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;
import expression.operation.BitwiseNot;

public class CheckedBitwiseNot extends BitwiseNot implements Checked {

    public CheckedBitwiseNot(CommonExpression first) {
        super(first);
    }
}
