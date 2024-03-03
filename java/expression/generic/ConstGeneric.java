package expression.generic;

public class ConstGeneric<T> extends AbstractGenericOperation<T> {
    private final T value;

    public ConstGeneric(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }
}
