package expression.parser;

import expression.CommonExpression;
import expression.Const;
import expression.Variable;
import expression.exception.*;
import expression.checked.*;
import expression.maths.AbstractMaths;
import expression.maths.IntegerMaths;
import expression.operation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExpressionParser implements Parser {
    private static final boolean CHECKED_MODE = "true".equalsIgnoreCase(System.getProperty("checkedMode"));

    private static final Map<Class<?>, Function<CommonExpression, CommonExpression>> UNARY_OPERATIONS = new HashMap<>();
    private static final Map<Class<?>, BiFunction<CommonExpression, CommonExpression, CommonExpression>> BINARY_OPERATIONS = new HashMap<>();

    static {
        BINARY_OPERATIONS.put(Add.class, CHECKED_MODE ? CheckedAdd::new : Add::new);
        BINARY_OPERATIONS.put(Subtract.class, CHECKED_MODE ? CheckedSubtract::new : Subtract::new);
        BINARY_OPERATIONS.put(Multiply.class, CHECKED_MODE ? CheckedMultiply::new : Multiply::new);
        BINARY_OPERATIONS.put(Divide.class, CHECKED_MODE ? CheckedDivide::new : Divide::new);
        UNARY_OPERATIONS.put(Negate.class, CHECKED_MODE ? CheckedNegate::new : Negate::new);

        BINARY_OPERATIONS.put(BitwiseAnd.class, CHECKED_MODE ? CheckedBitwiseAnd::new : BitwiseAnd::new);
        BINARY_OPERATIONS.put(BitwiseOr.class, CHECKED_MODE ? CheckedBitwiseOr::new : BitwiseOr::new);
        BINARY_OPERATIONS.put(BitwiseXor.class, CHECKED_MODE ? CheckedBitwiseXor::new : BitwiseXor::new);
        UNARY_OPERATIONS.put(BitwiseNot.class, CHECKED_MODE ? CheckedBitwiseNot::new : BitwiseNot::new);
        UNARY_OPERATIONS.put(BitwiseCount.class, CHECKED_MODE ? CheckedBitwiseCount::new : BitwiseCount::new);

        BINARY_OPERATIONS.put(Logarithm.class, CHECKED_MODE ? CheckedLogarithm::new : Logarithm::new);
        BINARY_OPERATIONS.put(Power.class, CHECKED_MODE ? CheckedPower::new : Power::new);

        BINARY_OPERATIONS.put(Min.class, CHECKED_MODE ? CheckedMin::new : Min::new);
        BINARY_OPERATIONS.put(Max.class, CHECKED_MODE ? CheckedMax::new : Max::new);
    }

    private Tokenizer mTokenizer;
    private AbstractMaths<?> mMaths;

    public ExpressionParser(AbstractMaths<?> maths) {
        mTokenizer = new Tokenizer(maths);
        mMaths = maths;
    }

    public ExpressionParser() {
        this(IntegerMaths.getInstance());
    }

    public CommonExpression parse(final String expression) throws ParsingException {
        mTokenizer = new Tokenizer(mMaths, expression);
        return evaluating();
    }

    private CommonExpression evaluating() throws ParsingException {
        return minMaxOperations();
    }

    private CommonExpression minMaxOperations() throws ArithmeticException, ParsingException {
        CommonExpression result = lowerOperations();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case MIN:
                    result = new Min(result, or());
                    break;
                case MAX:
                    result = new Max(result, or());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression or() throws ParsingException {
        CommonExpression result = xor();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case OR:
                    result = BINARY_OPERATIONS.get(BitwiseOr.class).apply(result, xor());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression xor() throws ParsingException {
        CommonExpression result = and();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case XOR:
                    result = BINARY_OPERATIONS.get(BitwiseXor.class).apply(result, and());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression and() throws ParsingException {
        CommonExpression result = lowerOperations();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case AND:
                    result = BINARY_OPERATIONS.get(BitwiseAnd.class).apply(result, lowerOperations());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression lowerOperations() throws ParsingException {
        CommonExpression result = higherOperations();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case ADD:
                    result = BINARY_OPERATIONS.get(Add.class).apply(result, higherOperations());
                    break;
                case SUB:
                    result = BINARY_OPERATIONS.get(Subtract.class).apply(result, higherOperations());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression higherOperations() throws ParsingException {
        CommonExpression result = maxOperations();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case MUL:
                    result = BINARY_OPERATIONS.get(Multiply.class).apply(result, maxOperations());
                    break;
                case DIV:
                    result = BINARY_OPERATIONS.get(Divide.class).apply(result, maxOperations());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression maxOperations() throws ParsingException {
        CommonExpression result = unaryOperations();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case LOG:
                    result = BINARY_OPERATIONS.get(Logarithm.class).apply(result, unaryOperations());
                    break;
                case POW:
                    result = BINARY_OPERATIONS.get(Power.class).apply(result, unaryOperations());
                    break;
                default:
                    return result;
            }
        }
    }

    private CommonExpression unaryOperations() throws ParsingException {
        mTokenizer.getNextToken();
        CommonExpression result;
        switch (mTokenizer.getCurrentToken()) {
            case NUM:
                Number value = mTokenizer.getValue();
                result = new Const(value);
                mTokenizer.getNextToken();
                break;
            case VAR:
                String name = mTokenizer.getName();
                result = new Variable(name);
                mTokenizer.getNextToken();
                break;
            case NEG:
                result = UNARY_OPERATIONS.get(Negate.class).apply(unaryOperations());
                break;
            case NOT:
                result = UNARY_OPERATIONS.get(BitwiseNot.class).apply(unaryOperations());
                break;
            case CNT:
                result = UNARY_OPERATIONS.get(BitwiseCount.class).apply(unaryOperations());
                break;
            case LOG_10:
                result = BINARY_OPERATIONS.get(Logarithm.class).apply(unaryOperations(), new Const(10));
                break;
            case POW_10:
                result = BINARY_OPERATIONS.get(Power.class).apply(new Const(10), unaryOperations());
                break;
            case OPENING_BRACE:
                result = evaluating();
                mTokenizer.getNextToken();
                break;
            default:
                return new Const(0);
        }
        return result;
    }
}
