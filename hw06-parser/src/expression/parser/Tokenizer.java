package expression.parser;

import expression.exception.*;
import expression.maths.AbstractMaths;

public class Tokenizer {

    private final AbstractMaths<?> maths;
    private String mExpression;
    private Token mCurrentToken;
    private int mCurrentPosition;
    private Number mValue;
    private String mName;
    private int mBracesBalance;
    private Token mLastToken;

    Tokenizer(AbstractMaths<?> maths, String expression) {
        this.maths = maths;
        this.mExpression = expression;
        this.mCurrentToken = Token.START;
        this.mCurrentPosition = 0;
        this.mValue = 0;
        this.mName = "";
    }

    Tokenizer(AbstractMaths<?> maths) {
        this(maths, "");
    }

    public Number getValue() {
        return mValue;
    }

    public String getName() {
        return mName;
    }

    public Token getCurrentToken() {
        return mCurrentToken;
    }

    public void getNextToken() throws ParsingException {
        nextToken();
    }

    public void close() throws ParsingException {
        if (mBracesBalance > 0) {
            throw new MissedClosingBraceException(mCurrentPosition, mExpression);
        }
        checkForBinaryOperations();
        mCurrentToken = Token.END;
    }

    private void nextToken() throws ParsingException {
        skipSpaces();
        if (!(isNotEnd())) {
            close();
            return;
        }
        char symbol = mExpression.charAt(mCurrentPosition);
        mCurrentPosition++;
        switch (symbol) {
            case '*':
                checkForBinaryOperations();
                if (isNotEnd() && (mExpression.charAt(mCurrentPosition) == '*')) {
                    mCurrentPosition++;
                    mCurrentToken = Token.POW;
                } else {
                    mCurrentToken = Token.MUL;
                }
                return;
            case '/':
                checkForBinaryOperations();
                if (mExpression.charAt(mCurrentPosition) == '/') {
                    mCurrentPosition++;
                    mCurrentToken = Token.LOG;
                } else {
                    mCurrentToken = Token.DIV;
                }
                return;
            case '+':
                checkForBinaryOperations();
                mCurrentToken = Token.ADD;
                return;
            case '-':
                if (mCurrentToken == Token.NUM || mCurrentToken == Token.CLOSING_BRACE || mCurrentToken == Token.VAR) {
                    checkForBinaryOperations();
                    mCurrentToken = Token.SUB;
                } else {
                    if (Character.isDigit(mExpression.charAt(mCurrentPosition))) {
                        mValue = getNumber(true);
                        mCurrentToken = Token.NUM;
                    } else {
                        checkForUnaryOperations();
                        mCurrentToken = Token.NEG;
                    }
                }
                return;
            case '(':
                checkForOperands();
                mBracesBalance++;
                mCurrentToken = Token.OPENING_BRACE;
                return;
            case ')':
                checkForBinaryOperations();
                mBracesBalance--;
                if (mBracesBalance < 0) {
                    throw new MissedClosingBraceException(mCurrentPosition, mExpression);
                }
                if (Token.isOperation(mCurrentToken) || (mCurrentToken == Token.OPENING_BRACE)) {
                    throw new MissedOperationException(mCurrentPosition, mExpression);
                }
                mCurrentToken = Token.CLOSING_BRACE;
                return;
            case '&':
                checkForBinaryOperations();
                mCurrentToken = Token.AND;
                return;
            case '|':
                checkForBinaryOperations();
                mCurrentToken = Token.OR;
                return;
            case '^':
                checkForBinaryOperations();
                mCurrentToken = Token.XOR;
                return;
            case '~':
                checkForUnaryOperations();
                mCurrentToken = Token.NOT;
                return;
            default:
                mCurrentPosition--;
                if (Character.isDigit(symbol)) {
                    mCurrentToken = Token.NUM;
                    mValue = getNumber(false);
                } else {
                    String word = getWord();
                    if (isName(word)) {
                        mName = word;
                        mCurrentToken = Token.VAR;
                    } else if (word.equals("count")) {
                        checkForUnaryOperations();
                        mCurrentToken = Token.CNT;
                    } else if (word.equals("log10")) {
                        checkForUnaryOperations();
                        mCurrentToken = Token.LOG_10;
                    } else if (word.equals("pow10")) {
                        checkForUnaryOperations();
                        mCurrentToken = Token.POW_10;
                    } else if (word.equals("min")) {
                        checkForBinaryOperations();
                        mCurrentToken = Token.MIN;
                    } else if (word.equals("max")) {
                        checkForBinaryOperations();
                        mCurrentToken = Token.MAX;
                    } else {
                        throw new UnknownSymbolException(word);
                    }
                }
        }
    }

    private void skipSpaces() {
        while (isNotEnd() && Character.isWhitespace(mExpression.charAt(mCurrentPosition))) {
            mCurrentPosition++;
        }
    }

    private boolean numberCharacter(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private Number getNumber(boolean negative) throws ParsingException {
        int begin = mCurrentPosition;
        while (isNotEnd() && numberCharacter(mExpression.charAt(mCurrentPosition))) {
            mCurrentPosition++;
        }
        String tmp = mExpression.substring(begin, mCurrentPosition);
        if (negative) tmp = "-" + tmp;
        return maths.parseNumber(tmp);
    }

    private String getWord() throws ParsingException {
        if (!Character.isLetter(mExpression.charAt(mCurrentPosition))) {
            throw new UnknownSymbolException("" + mExpression.charAt(mCurrentPosition));
        }
        int begin = mCurrentPosition;
        while (isNotEnd() && Character.isLetterOrDigit(mExpression.charAt(mCurrentPosition))) {
            mCurrentPosition++;
        }
        return mExpression.substring(begin, mCurrentPosition);
    }

    private void checkForBinaryOperations() throws MissedOperandException {
        if (mCurrentToken == Token.OPENING_BRACE || Token.isOperation(mCurrentToken) || mCurrentToken == Token.START) {
            throw new MissedOperandException(mCurrentPosition, mExpression);
        }
    }

    private void checkForOperands() throws MissedOperationException {
        if (mCurrentToken == Token.CLOSING_BRACE || Token.isOperand(mCurrentToken)) {
            throw new MissedOperationException(mCurrentPosition, mExpression);
        }
    }

    private void checkForUnaryOperations() throws MissedOperandException {
        if (!(isNotEnd())) {
            throw new MissedOperandException(mCurrentPosition, mExpression);
        }
    }

    private boolean isName(String word) {
        return (word.equals("x") || word.equals("y") || word.equals("z"));
    }

    private boolean isNotEnd() {
        return (mCurrentPosition < mExpression.length());
    }
}
