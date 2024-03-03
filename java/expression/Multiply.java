package expression;

import expression.generic.GenericOperation;

public class Multiply<T> extends AbstractBinaryOperation<T> {

    public Multiply(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        super(a, b, type);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected T solve(T a, T b) {
        return type.multiply(a, b);
    }

}
