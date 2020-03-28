package expression.exception;

public class MissedClosingBraceException extends ParsingException {

    public MissedClosingBraceException(int position, String expression) {
        super("missed closing brace at position: " + position + "\n" + expression + "\n" + getPlace(position, 1));
    }
}
