package queue;

import java.util.Objects;
import java.util.function.Predicate;

public class MyArrayQueueModuleTest {
    public static void main(String[] args) {
        Predicate<Object> nullable = Objects::isNull;
        fill("q1_");
        countPredicates(nullable);
        dump();
    }
    public static void fill(String prefix) {
        for (int i = 0; i < 20; i++) {
            ArrayQueueModule.enqueue(prefix + i);
        }
    }
    public static void countPredicates(Predicate<Object> predicate) {
        System.out.println(ArrayQueueModule.countIf(predicate));
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " +  ArrayQueueModule.dequeue());
        }
    }
}
