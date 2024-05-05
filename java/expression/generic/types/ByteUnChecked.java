package expression.generic.types;

import expression.generic.GenericOperation;

public class ByteUnChecked implements GenericOperation<Byte> {
    @Override
    public Byte add(Byte a, Byte b) {
        return (byte) (a + b);
    }

    @Override
    public Byte subtract(Byte a, Byte b) {
        return (byte) (a - b);
    }

    @Override
    public Byte multiply(Byte a, Byte b) {
        return (byte) (a * b);
    }

    @Override
    public Byte divide(Byte a, Byte b) {
        return (byte) (a / b);
    }

    @Override
    public Byte negate(Byte a) {
        return (byte) -a;
    }

    @Override
    public Byte min(Byte a, Byte b) {
        return null;
    }

    @Override
    public Byte max(Byte a, Byte b) {
        return null;
    }

    @Override
    public Byte parseConst(String s) {
        return (byte) Integer.parseInt(s);
    }

    @Override
    public Byte count(Byte a) {
        return null;
    }
}
