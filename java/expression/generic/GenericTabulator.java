package expression.generic;

import expression.Operation;
import expression.exceptions.EvaluateError;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParsingException;
import expression.types.DoubleType;
import expression.types.IntegerType;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private final static Map<String, GenericOperation<?>> modes = Map.of(
            "i", new IntegerType(),
            "d", new DoubleType()
    );
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        System.err.println(mode);
        System.err.println(expression);

        return solve(modes.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] solve(
            GenericOperation<T> type, String expression,
            int x1, int x2, int y1, int y2, int z1, int z2
    ) throws ParsingException {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        Operation expr = new ExpressionParser().parse(expression);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        result[i - x1][j - y1][k - z1] = expr.evaluate(
                                i, j, k
                        );
                    } catch (EvaluateError e) {
                        result[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}
