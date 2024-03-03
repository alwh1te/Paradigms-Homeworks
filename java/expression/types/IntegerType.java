package expression.types;

import expression.exceptions.*;
import expression.generic.GenericOperation;

public class IntegerType implements GenericOperation<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        if ((a > 0 && b > 0 && a > Integer.MAX_VALUE - b)
                || (a < 0 && b < 0 && a < Integer.MIN_VALUE - b)) {
            throw new AddOverflowException("can't add: " + a + " + " + b);
        }
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if ((a >= 0 && b < 0 && a > Integer.MAX_VALUE + b)
                || (a < 0 && b > 0 && a < Integer.MIN_VALUE + b)) {
            throw new SubtractOverflowException("can't subtract: " + a + " - " + b);
        }
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (a != 0 && b != 0 && (a > 0 && b > 0 && a > Integer.MAX_VALUE / b
                || a < 0 && b < 0 && a < Integer.MAX_VALUE / b
                || a > 0 && b < 0 &&
                b < Integer.MIN_VALUE / a || a < 0 && b > 0 && a < Integer.MIN_VALUE / b)) {
            throw new MultiplyOverflowException("can't multiply: " + a + " * " + b);
        }
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == 0 || (a == Integer.MIN_VALUE && b == -1)) {
            throw new DivisionByZeroException("can't divide: " + a + " / " + b);
        }
        return a / b;
    }

    @Override
    public Integer negate(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new NegateOverflowException("can't negate " + a);
        }
        return -a;
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return a < b ? a : b;
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return a > b ? a : b;
    }

    @Override
    public Integer parseConst(String s) {
        return Integer.parseInt(s);
    }
}
