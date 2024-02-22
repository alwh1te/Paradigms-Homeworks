package queue;


import java.util.Objects;
import java.util.function.Predicate;

public class MyArrayQueueTest {
    public static void fill(ArrayQueue queue, String prefix) {
        for (int i = 0; i < 20; i++) {
            queue.enqueue(prefix + i);
        }
    }

    public static void countPredicates(ArrayQueue queue, Predicate<Object> predicate) {
        System.out.println(queue.countIf(predicate));
    }
    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue.dequeue());
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        Predicate<Object> nullable = Objects::isNull;
        fill(queue, "q1_");
        countPredicates(queue, nullable);
        dump(queue);
    }
}