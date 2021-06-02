package ru.job4j.list;

public class SimpleQueue<T> {

    private final SimpleStack<T> in;
    private final SimpleStack<T> out;

    public SimpleQueue() {
        in = new SimpleStack<>();
        out = new SimpleStack<>();
    }

    private void transferAll() {
        while (in.size() > 0) {
            T entry = in.pop();
            out.push(entry);
        }
    }

    public T poll() {
        if (out.size() == 0) {
            transferAll();
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}
