package expression.generic;

import expression.Operation;

public interface GenericOperation<T> {
    Operation add(T a, T b);
    Operation subtract(T a, T b);
    Operation multiply(T a, T b);
    Operation divide(T a, T b);
}
