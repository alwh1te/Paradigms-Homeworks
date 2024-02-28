package expression;

public class Operators {
    private final OperatorsEnum type;
    private final int pos;
    public Operators(OperatorsEnum type, int pos) {
        this.type = type;
        this.pos = pos;
    }

    public OperatorsEnum getType() {
        return type;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
