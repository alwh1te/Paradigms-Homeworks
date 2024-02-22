package queue;


public class MyArrayQueueTest {
    public static void fill(ArrayQueue queue, String prefix) {
        for (int i = 0; i < 20; i++) {
            queue.enqueue(prefix + i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue.dequeue());
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue, "s1_");
        dump(queue);
    }
}