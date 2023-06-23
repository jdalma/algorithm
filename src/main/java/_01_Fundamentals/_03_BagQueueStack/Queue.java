package _01_Fundamentals._03_BagQueueStack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assert queue.dequeue() == 1;
        assert !queue.isEmpty();
        assert queue.dequeue() == 2;
        assert queue.dequeue() == 3;
        assert queue.dequeue() == null;

        queue.enqueue(1);
        assert queue.dequeue() == 1;
    }
}
