package expression;

import expression.generic.GenericOperation;

public class Divide<T> extends AbstractBinaryOperation<T> {

    public Divide(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        super(a, b, type);
    }

    @Override
    protected T solve(T a, T b) {
        return type.divide(a, b);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }
}
