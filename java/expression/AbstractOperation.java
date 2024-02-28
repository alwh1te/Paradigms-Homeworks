package expression;

import java.util.List;
import java.util.Objects;

public abstract class AbstractOperation implements Operation {
    private final Operation a;
    private final Operation b;

    public AbstractOperation(Operation a, Operation b) {
        this.a = a;
        this.b = b;
    }

    public int evaluate(int x) {
        return solve(a.evaluate(x), b.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return solve(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    protected abstract String getOperator();
    protected abstract int solve(int a, int b);

    @Override
    public int evaluate(List<Integer> variables) {
        return solve(a.evaluate(variables), b.evaluate(variables));
    }

    @Override
    public String toString() {
        return "(" + a + getOperator() + b + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractOperation that = (AbstractOperation) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, getOperator(), b) * 31;
    }
}