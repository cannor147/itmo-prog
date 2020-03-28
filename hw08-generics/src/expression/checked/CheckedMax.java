package expression.checked;

import expression.CommonExpression;
import expression.operation.Max;
import expression.operation.Min;

public class CheckedMax extends Max implements Checked {

    public CheckedMax(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
