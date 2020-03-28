package expression.exception;

public class MissedOperandException extends ParsingException {

    public MissedOperandException(int position, String expression) {
        super("missed operand at position: " + position + "\n" + expression + "\n" + getPlace(position, 1));
    }
}
