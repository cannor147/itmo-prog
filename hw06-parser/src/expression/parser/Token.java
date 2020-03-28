package expression.parser;

public enum Token {

    NUM, //constant expression
    VAR, //variable expression
    OPENING_BRACE, //opening brace
    CLOSING_BRACE, //closed brace

    NOT, //bitwise not operation
    NEG, //negation operation
    CNT, //count operation
    MUL, //multiply operation
    DIV, //divide operation
    ADD, //add operation
    SUB, //subtract operation
    AND, //bitwise and operation
    OR, //bitwise or operation
    XOR, //bitwise xor operation
    LOG, //logarithm operation
    POW, //power operation
    LOG_10, //logarithm to base 10 operation
    POW_10, //power to base 10 operation
    MIN, //min operation
    MAX, //max operation

    START, //start of parsing
    END; //end of parsing

    public static boolean isOperation(Token token) {
        if (isOperand(token) || isBrace(token) || isCommand(token)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBrace(Token token) {
        if ((token == OPENING_BRACE) || (token == CLOSING_BRACE)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperand(Token token) {
        if ((token == NUM) || (token == VAR)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCommand(Token token) {
        if ((token == START) || (token == END)) {
            return true;
        } else {
            return false;
        }
    }
}
