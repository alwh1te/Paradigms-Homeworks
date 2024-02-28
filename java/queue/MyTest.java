package queue;

public class MyTest {
    public static void main(String[] args) {
        Queue q1 = new ArrayQueue();
        Queue q2 = new LinkedQueue();
        fill(q1, "q1_");
        fill(q2, "q2_");
        dump(q1);
        dump(q2);
    }

    public static void dump(Queue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
    public static void fill(Queue queue, String prefix) {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(prefix + i);
        }
    }
}
