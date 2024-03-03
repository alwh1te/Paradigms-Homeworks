package expression;

import expression.generic.GenericOperation;


public class Add<T> extends AbstractOperation<T> {

    public Add(Operation<T> a, Operation<T> b, GenericOperation<T> type) {
        super(a, b, type);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected T solve(T a, T b) {
        return type.add(a, b);
    }
}
