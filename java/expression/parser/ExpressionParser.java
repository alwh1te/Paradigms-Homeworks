package expression.parser;

import expression.TripleExpression;
import expression.exceptions.TripleParser;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) throws Exception {
        return new BaseParser().parse(expression, true);
    }
}