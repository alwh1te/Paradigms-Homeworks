package queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyArrayQueueTest {
    public static void fill(ArrayQueue queue, String prefix) {
        for (int i = 0; i < 20; i++) {
            queue.enqueue(prefix + i);
        }
    }
    public static void fillDequeue(Deque<String> queue, String prefix) {
        for (int i = 0; i < 10; i++) {
            queue.add(prefix + i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue.dequeue());
        }
    }

    public static void main(String[] args) {
        ArrayQueue stack1 = new ArrayQueue();
        Deque<String> stack2 = new ArrayDeque<>();
        fill(stack1, "s1_");
        fillDequeue(stack2, "s2_");
        dump(stack1);
        dumpDeqeue(stack2);
    }

    private static void dumpDeqeue(Deque<String> queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue .pop());
        }
    }
}