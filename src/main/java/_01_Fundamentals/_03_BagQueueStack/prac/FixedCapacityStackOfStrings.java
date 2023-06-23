package _01_Fundamentals._03_BagQueueStack.prac;

public class FixedCapacityStackOfStrings {

    private String[] a;
    private int N;

    public FixedCapacityStackOfStrings(int cap) {
        this.a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public boolean isFull () {
        return a.length == N;
    }
    public String pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(3);

        stack.push("a");
        stack.push("b");
        stack.push("c");

        assert stack.isFull();
    }
}
