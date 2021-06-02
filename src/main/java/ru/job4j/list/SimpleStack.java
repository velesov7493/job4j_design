package ru.job4j.list;

public class SimpleStack<T> {

    private ForwardLinked<T> linked;

    public SimpleStack() {
        linked = new ForwardLinked<>();
    }

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public int size() {
        return linked.size();
    }
}
