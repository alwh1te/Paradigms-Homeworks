package queue;

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

    public static void main(String[] args) {
        ArrayQueueADT stack1 = ArrayQueueADT.create();
        ArrayQueueADT stack2 = ArrayQueueADT.create();
        fill(stack1, "s1_");
        fill(stack2, "s2_");
        dump(stack1);
        dump(stack2);
    }
}