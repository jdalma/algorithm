package baekjoon.mst.kruskal;

import java.io.*;
import java.util.*;

class 나만_안되는_연애 {
    static List<Edge> edges = new ArrayList<>();
    static int[] relation;
    static char[] gender;
    static int unionCost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int collegeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        relation = new int[collegeCount + 1];
        gender = new char[collegeCount + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= collegeCount ; i++){
            gender[i] = st.nextToken().toCharArray()[0];
        }
        for(int i = 0 ; i <= collegeCount ; i++) relation[i] = i;
        for(int i = 0 ; i < edgeCount ; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(node1 , node2 , cost));
        }

        Collections.sort(edges);

        int unionCount = 0;
        for(Edge edge : edges){
            if(Union(edge)) unionCount++;
        }

        bw.write(unionCount != collegeCount - 1 ? "-1" : String.valueOf(unionCost));
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean Union(Edge edge){
        if(gender[edge.node1] == gender[edge.node2]) return false;

        int node_1_parent = Find(edge.node1);
        int node_2_parent = Find(edge.node2);

        if(node_1_parent == node_2_parent) return false;

        relation[node_1_parent] = node_2_parent;

        unionCost += edge.cost;

        return true;
    }

    public static int Find(int node){
        if(relation[node] == node) return node;
        return relation[node] = Find(relation[node]);
    }

    static class Edge implements Comparable<Edge>{
        int node1;
        int node2;
        int cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

}
