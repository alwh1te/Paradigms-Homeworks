package queue;

import java.util.Objects;

public class ArrayQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Object element) {
        Objects.requireNonNull(element);
        this.ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

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

    public int size() {
        return size;
    }

    public Object dequeue() {
        assert size > 0;
        Object temp = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return temp;
    }

    public void clear() {
        elements = new Object[2];
        head = 0;
        tail = 0;
        size = 0;
    }
}
