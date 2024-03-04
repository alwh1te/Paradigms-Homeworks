package expression;


public class Variable<T> extends AbstractOperationElement<T>{
    public Variable(T value) {
        super(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    protected T evaluateImpl(T x, T y, T z) {
        return switch (value.toString()) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    @Override
    protected T evaluateImpl(T x) {
        return x;
    }
}
