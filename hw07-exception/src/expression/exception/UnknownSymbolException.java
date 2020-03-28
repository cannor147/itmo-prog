package expression.exception;

public class UnknownSymbolException extends ParsingException {

    public UnknownSymbolException(String word) {
        super("unknown symbols: " + word);
    }
}
