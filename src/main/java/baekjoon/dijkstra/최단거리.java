package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 최단거리 {
    static int[] result;
    static List<ArrayList<Edge>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        result = new int[vertex + 1];
        Arrays.fill(result , Integer.MAX_VALUE);
        int stanVertex = Integer.parseInt(br.readLine());
        for(int i = 0 ; i <= vertex ; i++) list.add(new ArrayList<>());
        for(int i = 0 ; i < edge ; i++){
            st = new StringTokenizer(br.readLine());
            int sVertex = Integer.parseInt(st.nextToken());
            int eVertex = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(sVertex).add(new Edge(eVertex , cost));
        }
        bfs(stanVertex , vertex);
        StringBuilder resultString = new StringBuilder();
        for(int i = 1 ; i < result.length; i++){
            if(result[i] == Integer.MAX_VALUE) resultString.append("INF\n");
            else resultString.append(result[i]).append("\n");
        }
        System.out.println(resultString);
    }

    public static void bfs(int stanVertex , int vertexSize){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        result[stanVertex] = 0;
        pq.offer(new Edge(stanVertex , 0));
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            int nowVertex = now.vertex;
            int nowCost = now.cost;
            if(nowCost > result[nowVertex]) continue;
            for(Edge edges : list.get(nowVertex)){
                if(result[edges.vertex] > nowCost + edges.cost){
                    result[edges.vertex] = nowCost + edges.cost;
                    pq.offer(new Edge(edges.vertex , nowCost + edges.cost));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge>{
        int vertex;
        int cost;
        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}
