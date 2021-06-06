package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements STree<E> {

    private final TreeNode<E> root;

    public SimpleTree(final E root) {
        this.root = new TreeNode<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = findBy(child).isEmpty();
        if (!result) {
            return false;
        }
        Optional<TreeNode<E>> treeParent = findBy(parent);
        result = treeParent.isPresent();
        if (result) {
            TreeNode<E> node = treeParent.get();
            result = node.addChild(new TreeNode<>(child));
        }
        return result;
    }

    @Override
    public Optional<TreeNode<E>> findBy(E value) {
        Optional<TreeNode<E>> result = Optional.empty();
        Queue<TreeNode<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            TreeNode<E> el = data.poll();
            if (el.getValue().equals(value)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return result;
    }
}
