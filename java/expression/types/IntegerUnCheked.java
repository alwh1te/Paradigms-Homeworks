package expression.types;

import expression.generic.GenericOperation;

public class IntegerUnCheked implements GenericOperation<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer negate(Integer a) {
        return -a;
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return null;
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return null;
    }

    @Override
    public Integer parseConst(String s) {
        return Integer.parseInt(s);
    }

    @Override
    public Integer count(Integer a) {
        return null;
    }
}
