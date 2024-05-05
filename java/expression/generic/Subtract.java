package expression.generic;

import expression.generic.GenericOperation;

public class Subtract<T> extends AbstractBinaryOperation<T> {

    public Subtract(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        super(a, b, type);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected T solve(T a, T b) {
        return type.subtract(a, b);
    }

}
