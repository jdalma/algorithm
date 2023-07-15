package _02_Sort;

import java.util.Arrays;

public class Template {

    public static void main(String[] args) {
        int[] a = new int[10];
        int N = 10;
        for (int i = 1 ; i < N ; i++) {
            for (int j = i + 1 ; j > 0 && less(a[j], a[j - 1]) ; j--) {
//                exch(a, j, j - 1);
            }
        }
    }

    public static boolean less(Comparable o1, Comparable o2) {
        return o1.compareTo(o2) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1 ; i < a.length ; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        Arrays.stream(a)
                .forEach(System.out::println);
    }
}
