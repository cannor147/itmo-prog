package expression.checked;

import expression.CommonExpression;
import expression.maths.CheckedIntegerMaths;

public interface Checked extends CommonExpression {

    @Override
    default int evaluate(int x) {
        return evaluate(x, CheckedIntegerMaths.getInstance());
    }

    @Override
    default int evaluate(int x, int y, int z) {
        return evaluate(x, y, z, CheckedIntegerMaths.getInstance());
    }
}
