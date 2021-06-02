package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    final class ElementsIterator implements Iterator<T> {

        private Node<T> current;

        public ElementsIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    private Node<T> head;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        size--;
        return result;
    }

    public void addFirst(T value) {
        head =
                head == null
                ? new Node<>(value, null)
                : new Node<>(value, head);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean revert() {
        if (size < 2) {
            return false;
        }
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }
}
