package expression;

import expression.generic.GenericOperation;

public abstract class AbstractUnaryOperation<T> implements Operation<T> {

    protected final Operation<T> value;
    protected final GenericOperation<T> type;

    protected AbstractUnaryOperation(Operation<T> value, GenericOperation<T> type) {
        this.value = value;
        this.type = type;
    }

    protected abstract String getOperator();

    @Override
    public String toString() {
        return getOperator() + value + ")";
    }
}
