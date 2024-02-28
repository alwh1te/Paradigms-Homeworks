package expression.exceptions;

public class NegateOverflowException extends EvaluateError {
    public NegateOverflowException(String message) {
        super(message);
    }
}
