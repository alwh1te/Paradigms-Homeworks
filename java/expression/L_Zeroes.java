//package expression;
//
//import java.util.List;
//
//public class L_Zeroes implements Operation {
//
//    private final Operation val;
//
//    @Override
//    public String toString() {
//        return "l0(" + val + ")";
//    }
//
//    public L_Zeroes(Operation val) {
//        this.val = val;
//    }
//
//    @Override
//    public int evaluate(int x) {
//        return Integer.numberOfLeadingZeros(val.evaluate(x));
//    }
//
//    @Override
//    public int evaluate(int x, int y, int z) {
//        return Integer.numberOfLeadingZeros(val.evaluate(x, y, z));
//    }
//
//    @Override
//    public int evaluate(List<Integer> variables) {
//        return val.evaluate(variables);
//    }
//}
