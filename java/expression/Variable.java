package expression;

import java.util.List;
import java.util.Objects;

public class Variable implements Operation {

    private String var;
    private int index;

    public Variable(String var) {
        this.var = var;
    }

    public Variable(int index) {
        this.index = index;
    }

    public Variable(String name, int index) {
        this.var = name;
        this.index = index;
    }


    @Override
    public int evaluate(int x) {
        return x;
    }

    public String toString() {
        return String.valueOf(var);
    }

    @Override
    public int evaluate(int a, int b, int c) {
        return switch (var) {
            case "x" -> a;
            case "y" -> b;
            case "z" -> c;
            default -> throw new IllegalStateException("Unexpected value: " + var);
        };
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return variables.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var) * 71;
    }
}
