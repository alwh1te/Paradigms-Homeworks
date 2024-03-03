package expression;

import expression.generic.GenericOperation;


public class Negate<T> extends AbstractUnaryOperation<T> {

    public Negate(Operation<T> value, GenericOperation<T> type) {
        super(value, type);
    }

    @Override
    protected String getOperator() {
        return "-(";
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return type.negate(value.evaluate(x, y, z));
    }

    @Override
    public T evaluate(T x) {
        return type.negate(value.evaluate(x));
    }
}
