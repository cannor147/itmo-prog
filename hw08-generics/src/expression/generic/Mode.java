package expression.generic;

public enum Mode {

    I, // for checked Integer
    D, // for Double
    BI, // for BigInteger
    U, // for Integer
    L, // for Long
    S, // for Short
    M; // for mistakes

    public static Mode toMode(String word) {
        try {
            return Mode.valueOf(word.toUpperCase());
        } catch (Exception e) {
            return M;
        }
    }
}
