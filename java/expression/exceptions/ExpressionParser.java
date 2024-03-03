package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;

import java.util.List;


public class ExpressionParser implements TripleParser, ListParser {
    @Override
    public Operation parse(String expression) throws ParsingException {
        return new BaseParser().parse(expression, true);
    }

    @Override
    public ListExpression parse(String expression, List<String> variables) throws Exception {
        return new BaseParser().parse(expression, true, variables);
    }
}