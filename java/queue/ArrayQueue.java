package queue;

import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 0;

    @Override
    protected void enqueueImpl(Object element) {
        this.ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    @Override
    protected Object elementImpl() {
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

    @Override
    public int countIfImpl(int ans, Predicate<Object> predicate) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(elements[(head + i) % elements.length])) {
                ans++;
            }
        }
        return ans;
    }

    @Override
    protected Object dequeueImpl() {
        Object temp = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return temp;
    }

    @Override
    protected void clearImpl() {
        elements = new Object[2];
        head = 0;
        tail = 0;
    }
}
