package expression.generic;

import expression.generic.GenericTabulator;

import java.util.Arrays;

public class MyTest {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.deepToString(new GenericTabulator().tabulate("d", "5min3", -8, 0, 1, 1, 1, 1)));
    }
}
