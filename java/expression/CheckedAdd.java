package expression;

import expression.exceptions.AddOverflowException;

public class CheckedAdd extends AbstractOperation {

    public CheckedAdd(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected int solve(int a, int b) {
        if ((a > 0 && b > 0 && a > Integer.MAX_VALUE - b)
                || (a < 0 && b < 0 && a < Integer.MIN_VALUE - b)) {
            throw new AddOverflowException("can't add: " + a + " + " + b);
        }
        return a + b;
    }
}
