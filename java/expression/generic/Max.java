package expression.generic;

import expression.generic.GenericOperation;

public class Max<T> extends AbstractBinaryOperation<T> {
    public Max(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        super(a, b, type);
    }

    @Override
    protected String getOperator() {
        return " max ";
    }

    @Override
    protected T solve(T a, T b) {
        return type.max(a, b);
    }
}
