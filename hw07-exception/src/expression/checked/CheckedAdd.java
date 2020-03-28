package expression.checked;

import expression.CommonExpression;
import expression.operation.Add;

public class CheckedAdd extends Add implements Checked {

    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }
}
