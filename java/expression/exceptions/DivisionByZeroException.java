package expression.exceptions;

public class DivisionByZeroException extends EvaluateError {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
