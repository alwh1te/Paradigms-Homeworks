package expression.parser;

import expression.OperatorsEnum;
import expression.Types;

import java.util.List;

public abstract class BaseMethods {

    protected boolean isBinaryOperator(final char c) {
        return c == '+' || c == '*' || c == '/' || c == '-'
                || c == '&' || c == '^' || c == '|' || c == 'm';
    }

    protected boolean isBinaryOperator(final OperatorsEnum c) {
        return c == OperatorsEnum.PLUS || c == OperatorsEnum.MINUS
                || c == OperatorsEnum.DIV || c == OperatorsEnum.MULTI
                || c == OperatorsEnum.AND || c == OperatorsEnum.OR || c == OperatorsEnum.XOR;
    }

    protected boolean isUnaryOperator(final char c) {
        return c == 'l' || c == 't';
    }

    protected boolean isUnaryOperator(final OperatorsEnum c) {
        return c == OperatorsEnum.NEGATE || c == OperatorsEnum.L_ZEROES || c == OperatorsEnum.T_ZEROES;
    }

    protected boolean isUnaryMinus(final char curChar, final Types lastElement) {
        return curChar == '-' && (lastElement != Types.VALUE);
    }

    protected boolean isNumber(final String expression, final int pos, final char curChar, final Types lastElement) {
        return Character.isDigit(curChar) || ((curChar == '-'
                && pos + 1 < expression.length() && Character.isDigit(expression.charAt(pos + 1))))
                && (lastElement != Types.VALUE);
    }

    protected List<Character> getChars(char c) {
        return switch (c) {
            case '+' -> List.of('+');
            case '-' -> List.of('-');
            case '*' -> List.of('*');
            case '/' -> List.of('/');
            case 'l', 't' -> List.of('l', 't', '0');
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
