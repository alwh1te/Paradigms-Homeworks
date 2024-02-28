package expression;

import java.util.List;

public class UnaryMinus implements Operation {

    private final Operation value;

    public UnaryMinus(Operation value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "-(" + value.toString() + ")";
    }

    @Override
    public int evaluate(int x) {
        return -(value.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -(value.evaluate(x, y, z));
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return 0;
    }
}
