package _02_Sort;

import edu.princeton.cs.algs4.*;

public class _04_PriorityQueueClient {

    public static void main(String[] args) {
        int N = 3;
        In[] streams = new In[N];

        streams[0] = new In("G D C A E B");
        streams[1] = new In("C B A A E D");
        streams[2] = new In("A A Z E Q Q");ㅖ

        merge(streams);
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
