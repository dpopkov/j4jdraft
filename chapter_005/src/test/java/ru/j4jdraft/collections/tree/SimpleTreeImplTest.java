package ru.j4jdraft.collections.tree;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleTreeImplTest {

    private final SimpleTree<Integer> tree = new SimpleTreeImpl<>(1);

    @Test
    public void whenAdd6ElementsThenFindLastReturns6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Optional<Node<Integer>> found = tree.findBy(6);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get().getValue(), is(6));
    }

    @Test
    public void whenFindNonExistingThenNotPresent() {
        tree.add(1, 2);
        assertThat(tree.findBy(6).isPresent(), is(false));
    }

    @Test
    public void whenIteratorThenIteratesAllElements() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        tree.add(7, 9);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        List<Integer> resultList = new ArrayList<>();
        while (it.hasNext()) {
            resultList.add(it.next());
        }
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(resultList, is(expected));
    }

    @Test
    public void whenAddDuplicateThenDoesNotContainDuplicate() {
        boolean rst = tree.add(1, 2);
        assertThat(rst, is(true));
        rst = tree.add(1, 2);
        assertThat(rst, is(false));
        Node<Integer> n = tree.findBy(1).orElseThrow();
        assertThat(n.getChildren().size(), is(1));
    }

    @Test
    public void whenAddToNonExistingParentThenDoesNotAdd() {
        boolean rst = tree.add(22, 3);
        assertThat(rst, is(false));
        Node<Integer> n = tree.findBy(1).orElseThrow();
        assertThat(n.getChildren().size(), is(0));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateAfterModificationThenException() {
        Iterator<Integer> it = tree.iterator();
        tree.add(1, 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateBeyondElementsThenException() {
        Iterator<Integer> it = tree.iterator();
        it.next();
        it.next();
    }
}
