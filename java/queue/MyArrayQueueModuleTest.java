package queue;

public class MyArrayQueueModuleTest {
    public static void main(String[] args) {
        fill("q1_");
        dump();
    }
    public static void fill(String prefix) {
        for (int i = 0; i < 20; i++) {
            ArrayQueueModule.enqueue(prefix + i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " +  ArrayQueueModule.dequeue());
        }
    }
}
