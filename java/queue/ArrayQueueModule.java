package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void enqueue(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public static Object element() {
        return elements[head];
    }

    private static void ensureCapacity() {
        if (size == elements.length) {
            Object[] newArray = new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[(head + i) % elements.length];
            }
            elements = newArray;
            head = 0;
            tail = size;
        }
    }

    public static int size() {
        return size;
    }

    public static Object dequeue() {
        assert size > 0;
        Object temp = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return temp;
    }

    public static void clear() {
        elements = new Object[2];
        head = 0;
        tail = 0;
        size = 0;
    }
}
