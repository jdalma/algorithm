package _02_Sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _04_PriorityQueueClient {

    public static void main(String[] args) {
    }

    private static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);

        for (int i = 0 ; i < N ; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        while(!pq.isEmpty()) {
            StdOut.println(pq.minKey());
            int i = pq.delMin();
            if(!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readLine());
            }
        }
    }
}
