package expression.generic;

public interface Operation<T> {
    T evaluate(T x, T y, T z);
    T evaluate(T x);
}
