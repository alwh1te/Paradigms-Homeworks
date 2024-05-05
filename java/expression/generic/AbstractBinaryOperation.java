package expression.generic;

public abstract class AbstractBinaryOperation<T> implements Operation<T> {
    private final Operation<T> a;
    private final Operation<T> b;
    protected final GenericOperation<T> type;

    public AbstractBinaryOperation(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        this.a = a;
        this.b = b;
        this.type = type;
    }

    public T evaluate(T x) {
        return solve(a.evaluate(x), b.evaluate(x));
    }

    public T evaluate(T x, T y, T z) {
        return solve(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    protected abstract String getOperator();
    protected abstract T solve(T a, T b);


    @Override
    public String toString() {
        return "(" + a + getOperator() + b + ")";
    }
}