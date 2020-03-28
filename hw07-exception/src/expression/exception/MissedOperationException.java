package expression.exception;

public class MissedOperationException extends ParsingException {

    public MissedOperationException(int position, String expression) {
        super("missed operation at position: " + position + "\n" + expression + "\n" + getPlace(position, 1));
    }
}
