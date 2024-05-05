package expression;

import java.util.List;

public class Multiply extends AbstractOperation {

    public Multiply(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected int solve(int a, int b) {
        return a * b;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return 0;
    }
}
