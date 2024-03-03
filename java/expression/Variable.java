package expression;

import java.util.Objects;

public class Variable<T> implements Operation<T> {

    private final String var;

    public Variable(String var) {
        this.var = var;
    }


    @Override
    public T evaluate(T x, T y, T z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + var);
        };
    }
    @Override
    public T evaluate(T x) {
        return x;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable<?> variable = (Variable<?>) o;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var) + 7;
    }
}
