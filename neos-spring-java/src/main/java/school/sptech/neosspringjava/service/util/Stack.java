package school.sptech.neosspringjava.service.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;

class Stack<T> {
    private LinkedList<T> elements = new LinkedList<>();

    public void push(T element) {
        elements.addFirst(element);
    }

    public T pop() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return elements.removeFirst();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}