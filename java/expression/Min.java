package expression;

import expression.generic.GenericOperation;

public class Min<T> extends AbstractBinaryOperation<T> {
    public Min(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        super(a, b, type);
    }

    @Override
    protected String getOperator() {
        return " min ";
    }

    @Override
    protected T solve(T a, T b) {
        return type.min(a, b);
    }
}
