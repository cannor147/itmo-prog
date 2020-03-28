package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;
import expression.operation.Logarithm;

public class CheckedLogarithm extends Logarithm implements Checked {

    public CheckedLogarithm(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
