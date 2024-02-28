package expression.exceptions;

public abstract class EvaluateError extends ExpressionExceptions {
    public EvaluateError(String message) {
        super(message);
    }
}
