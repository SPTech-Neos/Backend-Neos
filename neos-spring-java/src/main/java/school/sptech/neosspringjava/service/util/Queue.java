package school.sptech.neosspringjava.service.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;

class Queue<T> {
    private LinkedList<T> elements = new LinkedList<>();

    public void enqueue(T element) {
        elements.addLast(element);
    }

    public T dequeue() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements.removeFirst();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
