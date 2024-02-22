package queue;

import java.util.Objects;

public class ArrayQueue {
    // Inv: forall i=1..n: a'[i] = a[i]

    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    // Pre: queue != null
    // Post: R = (n = 0) && n' = n && Inv(n)
    public boolean isEmpty() {
        return size == 0;
    }

    // Pre: queue != null && element != null
    // Post: n' = n + 1 &&
    //       a'[n'] = element &&
    //       Inv(n)
    public void enqueue(Object element) {
        Objects.requireNonNull(element);
        this.ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    // Pre: queue != null && n > 0
    // Post: R = a[n] && n' = n && Inv(n)
    public Object element() {
        return elements[head];
    }

    private void ensureCapacity() {
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

    // Pre: queue != null
    // Post: R = n && n' = n && Inv(n)
    public int size() {
        return size;
    }

    // Pre: queue != null && n > 0
    // Post: R = a[n] && n' = n - 1 && Inv(n')
    public Object dequeue() {
        assert size > 0;
        Object temp = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return temp;
    }

    // Pre: queue != null
    // Post: queue - empty queue
    public void clear() {
        elements = new Object[2];
        head = 0;
        tail = 0;
        size = 0;
    }
}
