package expression;

public abstract class AbstractOperationElement<T> implements Operation<T> {

    protected final T value;

    public AbstractOperationElement(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return evaluateImpl(x, y, z);
    }

    protected abstract T evaluateImpl(T x, T y, T z);
    protected abstract T evaluateImpl(T x);
    @Override
    public T evaluate(T x) {
        return evaluateImpl(x);
    }
}
