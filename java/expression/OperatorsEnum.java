package expression;

public enum OperatorsEnum {
    OR(100), XOR(200), AND(300),
    MINUS(400), PLUS(400),
    MULTI(500), DIV(500),
    NEGATE(600), L_ZEROES(700), T_ZEROES(700),
    MIN(500), MAX(500),
    LEFT_BRACKETS(1),
    LOW_PRIORITY(-1);

    private final int value;

    OperatorsEnum(int value) {
        this.value = value;
    }

    public int getPriority() {
        return value;
    }
}
