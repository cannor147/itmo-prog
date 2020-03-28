package expression.exception;

public class MissedOpeningBraceException extends ParsingException {

    public MissedOpeningBraceException(int position, String expression) {
        super("missed opening brace at position: " + position + "\n" + expression + "\n" + getPlace(position, 1));
    }
}
