package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.util.*;

public class BaseParser extends BaseMethods {

    private Deque<Operation> result;
    private Deque<Operators> stack;
    private List<String> variables;
    private Types lastElement;
    private int pos;
    private char curChar;
    private String expression;
    private int len;
    private boolean checked;

    public Operation parse(final String expression, final boolean checked) throws ParsingException {
        this.initial(expression, checked);
        return parse();
    }

    public Operation parse(final String expression, final boolean checked, final List<String> vars) throws ParsingException {
        this.initial(expression, checked);
        this.variables = vars;
        return parse();
    }


    private Operation parse() throws ParsingException {
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
                result.push(new Const(Integer.parseInt(parseValue(false))));
                lastElement = Types.VALUE;
            } else if (isUnaryMinus(curChar, lastElement)) {
                stack.push(new Operators(OperatorsEnum.NEGATE, pos));
                lastElement = Types.UNARY_OPERATOR;
            } else if (isBinaryOperator(curChar)) {
                Operators op = parseOperator(getChars(curChar));
                while (!stack.isEmpty() && op.getType().getPriority() <= Objects.requireNonNull(stack.peek()).getType().getPriority()) {
                    solve();
                }
                stack.push(op);
                lastElement = Types.BINARY_OPERATOR;
            } else if (isUnaryOperator(curChar)) {
                stack.push(parseOperator(getChars(curChar)));
                lastElement = Types.UNARY_OPERATOR;
            } else {
                String var = parseValue(true);
                result.push(new Variable(var, variables.indexOf(var)));
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
            Operation a = result.pop();
            Operation b = result.pop();
            result.push(switch (op.getType()) {
                case PLUS -> checked ? new CheckedAdd(b, a) : new Add(b, a);
                case MINUS -> checked ? new CheckedSubtract(b, a) : new Subtract(b, a);
                case MULTI -> checked ? new CheckedMultiply(b, a) : new Multiply(b, a);
                case DIV -> checked ? new CheckedDivide(b, a) : new Divide(b, a);
                default -> throw new IllegalOperationException("Unexpected value: " + op + " at position: " + op.getPos());
            });
        } else if (isUnaryOperator(op.getType()) && !result.isEmpty()) {
            Operation value = result.pop();
            result.push(switch (op.getType()) {
                case NEGATE -> checked ? new CheckedNegate(value) : new UnaryMinus(value);
                case L_ZEROES -> new L_Zeroes(value);
                case T_ZEROES -> new T_Zeroes(value);
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
        return switch (op.toString()) {
            case "+" -> new Operators(OperatorsEnum.PLUS, pos);
            case "-" -> new Operators(OperatorsEnum.MINUS, pos);
            case "*" -> new Operators(OperatorsEnum.MULTI, pos);
            case "/" -> new Operators(OperatorsEnum.DIV, pos);
            case "l0" -> new Operators(OperatorsEnum.L_ZEROES, pos);
            case "t0" -> new Operators(OperatorsEnum.T_ZEROES, pos);
            default -> throw new IllegalElementException("Unexpected value: " + op);
        };
    }
    private void initial(final String expression, final boolean checked) {
        this.expression = expression;
        this.checked = checked;
        this.len = this.expression.length();
        this.lastElement = null;
        this.variables = List.of("x", "y", "z");
        result = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }
}