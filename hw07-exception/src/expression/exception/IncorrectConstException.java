package expression.exception;

public class IncorrectConstException extends ParsingException {

    public IncorrectConstException(String c) {
        super("incorrect const: " + c);
    }
}
