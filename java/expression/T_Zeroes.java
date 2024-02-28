package expression;

import java.util.List;

public class T_Zeroes implements Operation {

    private final Operation val;

    public T_Zeroes(Operation val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "t0(" + val + ")";
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfTrailingZeros(val.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfTrailingZeros(val.evaluate(x, y, z));
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return val.evaluate(variables);
    }
}
