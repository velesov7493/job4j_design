package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleList<E> {

    final class Node {

        private Node nPrev;
        private Node nNext;
        private E data;

        public Node(Node aPrev, Node aNext, E aData) {
            nPrev = aPrev;
            if (nPrev != null) {
                nPrev.nNext = this;
            }
            nNext = aNext;
            if (nNext != null) {
                nNext.nPrev = this;
            }
            data = aData;
        }

        public void unlink() {
            if (nPrev != null) {
                nPrev.nNext = nNext;
            }
            if (nNext != null) {
                nNext.nPrev = nPrev;
            }
        }
    }

    final class ElementsIterator implements Iterator<E> {

        private Node current;
        private int expectedModCount;

        public ElementsIterator() {
            current = first;
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            E result = current.data;
            current = current.nNext;
            return result;
        }
    }

    private int size;
    private int modCount;
    private Node first;
    private Node last;

    private Node findByIndex(int index) {
        Objects.checkIndex(index, size);
        Node current = first;
        for (int i = 1; i <= index; i++) {
            current = current.nNext;
        }
        return current;
    }

    @Override
    public void add(E value) {
        if (first == null) {
            first = new Node(null, null, value);
            last = first;
        } else {
            last = new Node(last, null, value);
        }
        modCount++;
        size++;
    }

    @Override
    public void remove(int index) {
        Node rNode = findByIndex(index);
        if (index == 0) {
            first = rNode.nNext;
        } else if (index == size) {
            last = rNode.nPrev;
        }
        rNode.unlink();
        rNode = null;
        modCount++;
        size--;
    }

    @Override
    public E get(int index) {
        Node result = findByIndex(index);
        return result.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementsIterator();
    }
}