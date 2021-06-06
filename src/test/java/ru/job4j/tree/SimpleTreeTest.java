package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        STree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        STree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddExistingChildThenFalse() {
        STree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertFalse(tree.add(1, 2));
    }

    @Test
    public void whenParentNotExistThenFalse() {
        STree<Integer> tree = new SimpleTree<>(1);
        assertFalse(tree.add(2, 3));
    }
}