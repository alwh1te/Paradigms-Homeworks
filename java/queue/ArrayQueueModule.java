package queue;

import java.util.Objects;
import java.util.function.Predicate;

// Model: arr[1]...arr[n]
public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;

    // Pre: True
    // Post: R = (n = 0) && n' = n && Inv(n)
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pre: element != null
    // Post: n' = n + 1 &&
    //       a'[n'] = element &&
    //       Inv(n)
    public static void enqueue(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    // Pre: n > 0
    // Post: R = a[n] && n' = n && Inv(n)
    public static Object element() {
        return elements[head];
    }

    // Pre: True
    // Post: R = n && n' = n && Inv(n)
    public static int size() {
        return size;
    }

    // Pred: predicate != null
    // Post: Result = arr.length = i = 0..n : predicate.test(arr[i]) == True
    public static int countIf(Predicate<Object> predicate) {
        int ans = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(elements[(head + i) % elements.length])) {
                ans++;
            }
        }
        return ans;
    }

    // Pre: n > 0
    // Post: R = a[n] && n' = n - 1 && Inv(n')
    public static Object dequeue() {
        assert size > 0;
        Object temp = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return temp;
    }

    // Pre: True
    // Post: queue.isEmpty == True
    public static void clear() {
        elements = new Object[2];
        head = 0;
        tail = 0;
        size = 0;
    }

    private static void ensureCapacity() {
        if (size == elements.length) {
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, head, newArray, 0, elements.length - head);
            System.arraycopy(elements, 0, newArray, elements.length - head, tail);
            elements = newArray;
            head = 0;
            tail = size;
        }
    }
}
