package expression;

public class Const<T> extends AbstractOperationElement<T>{
    public Const(T value) {
        super(value);
    }

    @Override
    protected T evaluateImpl(T x, T y, T z) {
        return value;
    }

    @Override
    protected T evaluateImpl(T x) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
