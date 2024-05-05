package expression.generic.types;

import expression.generic.GenericOperation;

public class BooleanType implements GenericOperation<Boolean> {
    @Override
    public Boolean add(Boolean a, Boolean b) {
        return a || b;
    }

    @Override
    public Boolean subtract(Boolean a, Boolean b) {
        return a ^ b;
    }

    @Override
    public Boolean multiply(Boolean a, Boolean b) {
        return a && b;
    }

    @Override
    public Boolean divide(Boolean a, Boolean b) {
        return null;
    }

    @Override
    public Boolean negate(Boolean a) {
        return !a;
    }

    @Override
    public Boolean min(Boolean a, Boolean b) {
        return null;
    }

    @Override
    public Boolean max(Boolean a, Boolean b) {
        return null;
    }

    @Override
    public Boolean parseConst(String s) {
        return Integer.parseInt(s) != 0;
    }

    @Override
    public Boolean count(Boolean a) {
        return true;
    }
}
