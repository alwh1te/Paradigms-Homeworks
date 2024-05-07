package expression.exceptions;

import expression.AbstractOperation;
import expression.Operation;

public class CheckedDivide extends AbstractOperation {
    public CheckedDivide(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }

    @Override
    protected int solve(int a, int b) {
        if (b == 0 || (a == Integer.MIN_VALUE && b == -1)) {
            throw new DivisionByZeroException("can't divide: " + a + " / " + b);
        }
        return a / b;
    }
}
