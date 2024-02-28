package expression;

import java.util.List;
import java.util.Objects;

public class Const implements Operation {

    private final int value;

    public Const(int value) {
        this.value = value;
    }


    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int evaluate(int a, int b, int c) {
        return value;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return value == aConst.value;
    }

    @Override
    public int hashCode() {
        return (Objects.hash(value) + 34) * 17;
    }
}
