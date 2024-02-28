package expression;

import expression.exceptions.NegateOverflowException;

import java.util.List;

public class CheckedNegate implements Operation {
    private final Operation value;

    public CheckedNegate(Operation value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "-(" + value.toString() + ")";
    }


    @Override
    public int evaluate(int x) {
        return -(isIllegal(value.evaluate(x)));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -(isIllegal(value.evaluate(x, y, z)));
    }

    private int isIllegal(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new NegateOverflowException("can't negate " + value);
        }
        return x;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return -isIllegal((value.evaluate(variables)));
    }
}
