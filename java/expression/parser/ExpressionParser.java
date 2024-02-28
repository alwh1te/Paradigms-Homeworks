package expression.parser;

import expression.TripleExpression;
import expression.exceptions.ParsingException;
import expression.exceptions.TripleParser;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) throws ParsingException {
        return new BaseParser().parse(expression, false);
    }
}