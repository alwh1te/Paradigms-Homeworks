package expression;

public class Subtract extends AbstractOperation {

    public Subtract(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected int solve(int a, int b) {
        return a - b;
    }
}
