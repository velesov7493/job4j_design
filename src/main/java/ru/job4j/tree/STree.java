package ru.job4j.tree;

import java.util.Optional;

public interface STree<E> {

    boolean add(E parent, E child);

    Optional<TreeNode<E>> findBy(E value);
}
