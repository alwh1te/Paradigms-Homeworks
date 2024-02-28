package queue;

public class MyLinkedQueueTest {
    public static void main(String[] args) {
        Queue q1 = new LinkedQueue();
        fill(q1, "q1_");
        dump(q1);
    }

    public static void fill(Queue queue, String prefix) {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(prefix + i);
        }
    }

    public static void dump(Queue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
