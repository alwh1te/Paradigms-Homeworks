package queue;

import java.util.Objects;

public class ArrayQueueADT {
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Object[] elements = new Object[2];

    // Pre: true
    // Post: R.n = 0
    public static ArrayQueueADT create() {
        ArrayQueueADT queue = new ArrayQueueADT();
        queue.elements = new Object[2];
        return queue;
    }

    // Pre: queue != null && element != null
    // Post: n' = n + 1 &&
    //       a'[n'] = element &&
    //       immutable(n)
    public static void enqueue(ArrayQueueADT queue, Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue,queue.size + 1);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
        queue.size++;
    }

    // Pre: queue != null
    // Post: n' = n && immutable(n)
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (queue.size == queue.elements.length) {
            Object[] newArray = new Object[queue.elements.length * 2];
            for (int i = 0; i < queue.size; i++) {
                newArray[i] = queue.elements[(queue.head + i) % queue.elements.length];
            }
            queue.elements = newArray;
            queue.head = 0;
            queue.tail = queue.size;
        }
    }

    // Pre: queue != null && n > 0
    // Post: R = a[n] && n' = n - 1 && immutable(n')
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object temp = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return temp;
    }

    // Pre: queue != null && n > 0
    // Post: R = a[n] && n' = n && immutable(n)
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;

        return queue.elements[queue.head];
    }

    // Pre: queue != null
    // Post: R = n && n' = n && immutable(n)
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // Pre: queue != null
    // Post: R = (n = 0) && n' = n && immutable(n)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[2];
        queue.size = 0;
        queue.head = 0;
        queue.tail = 0;
    }
}
