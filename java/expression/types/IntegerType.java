package expression.types;

import expression.*;
import expression.generic.GenericOperation;

public class IntegerType implements GenericOperation<Integer> {
    @Override
    public Operation add(Integer a, Integer b) {
        return new CheckedAdd(new Const(b), new Const(b));
    }

    @Override
    public Operation subtract(Integer a, Integer b) {
        return new CheckedSubtract(new Const(b), new Const(b));
    }

    @Override
    public Operation multiply(Integer a, Integer b) {
        return new CheckedMultiply(new Const(b), new Const(b));
    }

    @Override
    public Operation divide(Integer a, Integer b) {
        return new CheckedDivide(new Const(b), new Const(b));
    }
}
