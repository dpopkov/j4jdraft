package ru.j4jdraft.io;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class BreadthFirst {
    private static class Node {
        private final int value;
        private final List<Node> nodes;

        private Node(int value) {
            this.value = value;
            nodes = new ArrayList<>();
        }

        private void add(Node n) {
            nodes.add(n);
        }
    }

    private final Deque<Node> queue = new LinkedList<>();

    void process(Node node, Consumer<Node> consumer) {
        queue.add(node);
        while(!queue.isEmpty()) {
            Node n = queue.remove();
            consumer.accept(n);
            queue.addAll(n.nodes);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        Node n1, n2, n3;
        root.add(n1 = new Node(20));
        root.add(n2 = new Node(30));
        root.add(n3 = new Node(40));
        n1.add(new Node(50));
        n1.add(new Node(60));
        n2.add(new Node(70));
        n3.add(new Node(80));
        n3.add(new Node(90));
        n3.add(new Node(99));
        new BreadthFirst().process(root, n -> System.out.print(n.value + " "));
    }
}
