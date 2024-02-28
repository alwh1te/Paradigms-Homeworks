package queue;

import java.util.Objects;
import java.util.function.Predicate;

public class ArrayQueueADT {
    // Inv: forall i=1..n: a'[i] = a[i]
    private int size;
    private int head;
    private int tail;
    private Object[] elements = new Object[2];

    // Pre: true
    // Post: R.n = 2
    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    // Pre: queue != null && element != null
    // Post: n' = n + 1 &&
    //       a'[n'] = element &&
    //       Inv(n)
    public static void enqueue(ArrayQueueADT queue, Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
        queue.size++;
    }


    // Pre: queue != null && n > 0
    // Post: R = a[n] && n' = n - 1 && Inv(n')
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object temp = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return temp;
    }

    // Pred: queue != null && predicate != null
    // Post: R = arr.length = n : 0..n : predicate.test(arr[n]) == True
    public static int countIf(ArrayQueueADT queue, Predicate<Object> predicate) {
        int ans = 0;
        for (int i = 0; i < queue.size; i++) {
            if (predicate.test(queue.elements[(queue.head + i) % queue.elements.length])) {
                ans++;
            }
        }
        return ans;
    }

    // Pre: queue != null && n > 0
    // Post: R = a[n] && n' = n && Inv(n)
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.head];
    }

    // Pre: queue != null
    // Post: R = n && n' = n && Inv(n)
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // Pre: queue != null
    // Post: R = (n = 0) && n' = n && Inv(n)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pre: queue != null
    // Post: queue : queue.isEmpty() == True
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[2];
        queue.size = 0;
        queue.head = 0;
        queue.tail = 0;
    }

    private static void ensureCapacity(ArrayQueueADT queue) {
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
}