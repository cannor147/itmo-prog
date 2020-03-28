package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;
import expression.operation.BitwiseCount;

public class CheckedBitwiseCount extends BitwiseCount implements Checked {

    public CheckedBitwiseCount(CommonExpression first) {
        super(first);
    }
}
