package expression.generic;

import expression.Operation;
import expression.exceptions.*;
import expression.parser.BaseParser;
import expression.types.*;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private final static Map<String, GenericOperation<?>> modes = Map.of(
            "i", new IntegerType(),
            "d", new DoubleType(),
            "bi", new BigIntegerType()
    );
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return solve(modes.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] solve(
            GenericOperation<T> type, String expression,
            int x1, int x2, int y1, int y2, int z1, int z2
    ) throws ParsingException {

        int xSize = Math.abs(x2-x1);
        int ySize = Math.abs(y2-y1);
        int zSize = Math.abs(z2-z1);
        Object[][][] res = new Object[xSize + 1][ySize + 1][zSize + 1];
        Operation<T> expr = new BaseParser().parse(expression, type);
        for (int i = 0; i <= xSize; i++) {
            for (int j = 0; j <= ySize; j++) {
                for (int k = 0; k <= zSize; k++) {
                    try {
                        res[i][j][k] = expr.evaluate(
                                type.parseConst(Integer.toString(i+x1)),
                                type.parseConst(Integer.toString(j+y1)),
                                type.parseConst(Integer.toString(k+z1)));
                    } catch (EvaluateError | ArithmeticException e) {
                        res[i][j][k] = null;
                    }
                }
            }
        }
        return res;
    }
}
