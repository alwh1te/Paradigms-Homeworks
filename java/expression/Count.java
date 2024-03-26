package expression;

import expression.generic.GenericOperation;

public class Count extends AbstractUnaryOperation<Integer> {
    protected Count(Operation<Integer> value, GenericOperation<Integer> type) {
        super(value, type);
    }

    @Override
    protected String getOperator() {
        return "count";
    }

    @Override
    public Integer evaluate(Integer x, Integer y, Integer z) {
        return null;
    }

    @Override
    public Integer evaluate(Integer x) {
        return type.count(x);
    }
}
