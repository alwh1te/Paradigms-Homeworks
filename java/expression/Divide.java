package expression;

public class Divide extends AbstractOperation {

    public Divide(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }

    @Override
    protected int solve(int a, int b) {
        return a / b;
    }
}
