package expression.checked;

import expression.CommonExpression;
import expression.Const;
import expression.maths.CheckedIntegerMaths;
import expression.operation.Power;

public class CheckedPower extends Power implements Checked {

    public CheckedPower(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
