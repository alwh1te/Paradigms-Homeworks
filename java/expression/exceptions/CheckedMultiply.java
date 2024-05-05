package expression.exceptions;

import expression.AbstractOperation;
import expression.Operation;

import java.util.List;

public class CheckedMultiply extends AbstractOperation {

    public CheckedMultiply(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected int solve(int a, int b) {
        if (a != 0 && b != 0 && (a > 0 && b > 0 && a > Integer.MAX_VALUE / b
                || a < 0 && b < 0 && a < Integer.MAX_VALUE / b
                || a > 0 && b < 0 &&
                b < Integer.MIN_VALUE / a || a < 0 && b > 0 && a < Integer.MIN_VALUE / b)) {
            throw new MultiplyOverflowException("can't multiply: " + a + " * " + b);
        }
        return a * b;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return 0;
    }
}
