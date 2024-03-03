package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.generic.GenericOperation;

import java.util.*;

public class BaseParser<T> extends BaseMethods {

    private Deque<Operation<T>> result;
    private Deque<Operators> stack;
    private List<String> variables;
    private Types lastElement;
    private int pos;
    private char curChar;
    private String expression;
    private int len;
    private GenericOperation<T> type;

    public Operation<T> parse(final String expression, final GenericOperation<T> type) throws ParsingException {
        this.initial(expression, type);
        return parse();
    }

    private Operation<T> parse() throws ParsingException {
        for (pos = 0; pos < len; pos++) {
            curChar = expression.charAt(pos);
            if (Character.isWhitespace(curChar)) {
                continue;
            }
            if (curChar == '(') {
                stack.push(new Operators(OperatorsEnum.LEFT_BRACKETS, pos));
            } else if (curChar == ')' && lastElement != Types.BINARY_OPERATOR) {
                while (stack.peek() == null || stack.peek().getType() != OperatorsEnum.LEFT_BRACKETS) {
                    if (stack.peek() == null) {
                        throw new IncorrectBracketsSequence("No opening brackets");
                    }
                    solve();
                }
                stack.pop();
            } else if (isNumber(expression, pos, curChar, lastElement)) {
                result.push(new Const<>(type.parseConst(parseValue(false))));
                lastElement = Types.VALUE;
            } else if (isUnaryMinus(curChar, lastElement)) {
                stack.push(new Operators(OperatorsEnum.NEGATE, pos));
                lastElement = Types.UNARY_OPERATOR;
            } else if (isBinaryOperator(curChar)) {
                Operators op = parseOperator(getChars(curChar));
                while (!stack.isEmpty() && op.getType().getPriority() <= stack.peek().getType().getPriority()) {
                    solve();
                }
                stack.push(op);
                lastElement = Types.BINARY_OPERATOR;
            } else if (isUnaryOperator(curChar)) {
                stack.push(parseOperator(getChars(curChar)));
                lastElement = Types.UNARY_OPERATOR;
            } else {
                String var = parseValue(true);
                result.push(new Variable<>(var));
            }
        }
        while(!stack.isEmpty()) {
            solve();
        }
        if (result.size() != 1) {
            throw new IllegalStateException("Incorrect expression structure");
        }
        return result.pop();
    }

    private void solve() throws ParsingException {
        if (stack.isEmpty() || result.isEmpty()) {
            throw new NoArgumentException("Empty stack");
        }
        Operators op = stack.pop();
        if (isBinaryOperator(op.getType()) && result.size() >= 2) {
            Operation<T> a = result.pop();
            Operation<T> b = result.pop();
            result.push(switch (op.getType()) {
                case PLUS -> new Add<>(b, a, type);
                case MINUS -> new Subtract<>(b, a, type);
                case MULTI -> new Multiply<>(b, a, type);
                case DIV -> new Divide<>(b, a, type);
                default -> throw new IllegalOperationException("Unexpected value: " + op + " at position: " + op.getPos());
            });
        } else if (isUnaryOperator(op.getType()) && !result.isEmpty()) {
            Operation<T> value = result.pop();
            result.push(switch (op.getType()) {
                case NEGATE -> new Negate<>(value, type);
                default -> throw new IllegalOperationException("Unexpected operator: " + op + " at position: " + op.getPos());
            });
        } else {
            throw new NoArgumentException("Not enough arguments for operation: " + op + " at position: " + op.getPos());
        }
    }

    private String parseValue(final boolean var) {
        StringBuilder sb = new StringBuilder();
        sb.append(curChar);
        while(pos < len) {
            pos++;
            if (pos < len) {
                char newChar = expression.charAt(pos);
                if (Character.isDigit(newChar)
                        || (!Character.isWhitespace(newChar)
                        && !isBinaryOperator(newChar)
                        && !Character.isDigit(newChar)
                        && newChar != ')' && var)) {
                    sb.append(newChar);
                } else {
                    pos--;
                    break;
                }
            }
        }
        if (!variables.contains(sb.toString()) && var) {
            throw new IllegalArgumentException(sb.toString());
        }
        lastElement = Types.VALUE;
        return sb.toString();
    }

    private Operators parseOperator(final List<Character> set) throws ParsingException {
        StringBuilder op = new StringBuilder();
        op.append(curChar);
        while(pos < len && op.length() < set.size()) {
            pos++;
            if (pos < len) {
                char newChar = expression.charAt(pos);
                if (set.contains(newChar)) {
                    op.append(newChar);
                } else {
                    pos--;
                    break;
                }
            }
        }
        if ((op.toString().equals("l0") || op.toString().equals("t0"))
                && pos + 1 < len && !(Character.isWhitespace(expression.charAt(pos + 1))
                || expression.charAt(pos + 1) == '(')) {
            throw new IllegalOperationException("Illegal operation: " + op + expression.charAt(pos + 1));
        }
        return new Operators(switch (op.toString()) {
            case "+" -> OperatorsEnum.PLUS;
            case "-" -> OperatorsEnum.MINUS;
            case "*" -> OperatorsEnum.MULTI;
            case "/" -> OperatorsEnum.DIV;
            case "min" -> OperatorsEnum.MIN;
            case "max" -> OperatorsEnum.MAX;
            case "l0" -> OperatorsEnum.L_ZEROES;
            case "t0" -> OperatorsEnum.T_ZEROES;
            default -> throw new IllegalElementException("Unexpected value: " + op);
        }, pos);
    }
    private void initial(final String expression, final GenericOperation<T> type) {
        this.expression = expression;
        this.type = type;
        this.len = this.expression.length();
        this.lastElement = null;
        this.variables = List.of("x", "y", "z");
        result = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }
}