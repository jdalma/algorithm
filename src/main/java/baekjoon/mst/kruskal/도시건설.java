package baekjoon.mst.kruskal;

import java.io.*;
import java.util.*;

class 도시건설 {
    static List<Edge> edges = new ArrayList<>();
    static int[] relation;
    static boolean[] checked;
    static long sumCost = 0 , minCost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        relation = new int[nodeCount + 1];
        checked = new boolean[nodeCount + 1];
        for (int i = 0; i < relation.length; i++) relation[i] = i;
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            sumCost += cost;
            edges.add(new Edge(node1, node2, cost));
        }

        Collections.sort(edges);

        int unionCount = 0;
        for (Edge edge : edges) {
            if(Union(edge)) unionCount++;
        }

        bw.write(unionCount != nodeCount - 1 ? "-1" : String.valueOf(sumCost - minCost));
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean Union(Edge edge) {
        int node_1_Parent = Find(edge.node1);
        int node_2_Parent = Find(edge.node2);

        if (node_1_Parent == node_2_Parent) return false;

        relation[Math.min(node_1_Parent , node_2_Parent)] = Math.max(node_1_Parent , node_2_Parent);

        minCost += edge.cost;
        return true;
    }

    public static int Find(int node) {
        if (node == relation[node]) return node;
        return relation[node] = Find(relation[node]);
    }

    static class Edge implements Comparable<Edge>{
        int node1;
        int node2;
        long cost;

        public Edge(int node1, int node2, long cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (this.cost - o.cost);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "node1=" + node1 +
                    ", node2=" + node2 +
                    ", cost=" + cost +
                    '}';
        }
    }
}
