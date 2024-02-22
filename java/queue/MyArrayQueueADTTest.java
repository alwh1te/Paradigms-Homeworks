package queue;

import java.util.Objects;
import java.util.function.Predicate;

public class MyArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue, String prefix) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, prefix + i);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
//                            queue.ArrayQueueADT.peek(queue) + " " +
                            ArrayQueueADT.dequeue(queue)
            );
        }
    }

    public static void countPredicates(ArrayQueueADT queue, Predicate<Object> predicate) {
        System.out.println(ArrayQueueADT.countIf(queue, predicate));
    }

    public static void main(String[] args) {
        Predicate<Object> nullable = Objects::isNull;
        ArrayQueueADT queue1 = ArrayQueueADT.create();
        ArrayQueueADT queue2 = ArrayQueueADT.create();
        fill(queue1, "q1_");
        fill(queue2, "q2_");
        countPredicates(queue1, nullable);
        countPredicates(queue2, nullable);
        dump(queue1);
        dump(queue2);
    }
}