package _02_Sort;

import java.util.Arrays;

import static _02_Sort.Template.*;

public class _01_SelectionSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0 ; i < N ; i++) {
            int min = i;
            for (int j = i + 1 ; j < N ; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = new Comparable[] {5, 2, 3, 1};
        sort(arr);
        show(arr);
    }
}
