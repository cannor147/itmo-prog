package expression;

import expression.maths.AbstractMaths;

public class Variable implements CommonExpression {

    private String mName;

    public Variable(String name) {
        this.mName = name;
    }

    @Override
    public <T extends Number> T evaluate(T x, AbstractMaths<T> maths) {
        if ("x".equals(mName)) {
            return x;
        }
        return maths.getNull();
    }

    @Override
    public <T extends Number> T evaluate(T x, T y, T z, AbstractMaths<T> maths) {
        switch(mName) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: return maths.getNull();
        }
    }
}
