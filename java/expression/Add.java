package expression;

public class Add extends AbstractOperation {

    public Add(Operation a, Operation b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected int solve(int a, int b) {
        return a + b;
    }
}
