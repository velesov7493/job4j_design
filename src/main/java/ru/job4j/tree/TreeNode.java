package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<E> {

    private final E value;
    private final List<TreeNode<E>> children;

    public TreeNode(E value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public E getValue() {
        return value;
    }

    public List<TreeNode<E>> getChildren() {
        return new ArrayList<>(children);
    }

    public boolean addChild(TreeNode<E> child) {
        return children.add(child);
    }
}
