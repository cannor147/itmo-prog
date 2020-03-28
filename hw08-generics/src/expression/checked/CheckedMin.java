package expression.checked;

import expression.CommonExpression;
import expression.operation.Add;
import expression.operation.Min;

public class CheckedMin extends Min implements Checked {

    public CheckedMin(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
