package expression.generic;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        System.err.println(mode);
        System.err.println(expression);
        return new Object[2][2][2];
    }
}
