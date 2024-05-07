package expression.exceptions;

import expression.AbstractOperation;
import expression.Operation;

public class CheckedSubtract extends AbstractOperation {

    public CheckedSubtract(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected int solve(int a, int b) {
        if ((a >= 0 && b < 0 && a > Integer.MAX_VALUE + b)
                || (a < 0 && b > 0 && a < Integer.MIN_VALUE + b)) {
            throw new SubtractOverflowException("can't subtract: " + a + " - " + b);
        }
        return a - b;
    }
}
