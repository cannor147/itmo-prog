package expression.exception;

public class ParsingException extends Exception {

    public ParsingException(final String message) {
        super(message);
    }

    static protected String getPlace(final int position, final int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < position; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append('^');
        for (int i = 1; i < length; i++) {
            stringBuilder.append('~');
        }
        return stringBuilder.toString();
    }

}
