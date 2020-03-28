package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;
import expression.operation.BitwiseXor;

public class CheckedBitwiseXor extends BitwiseXor implements Checked {

    public CheckedBitwiseXor(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
