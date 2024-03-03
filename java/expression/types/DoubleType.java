package expression.types;

import expression.generic.GenericOperation;
public class DoubleType implements GenericOperation<Double> {

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double min(Double a, Double b) {
        return a < b ? a : b;
    }

    @Override
    public Double max(Double a, Double b) {
        return a > b ? a : b;
    }

    @Override
    public Double parseConst(String s) {
        return Double.parseDouble(s);
    }
}
