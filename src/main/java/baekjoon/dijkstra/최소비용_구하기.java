package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 최소비용_구하기 {
    static List<List<Edge>> list = new ArrayList<>();
    static int[] costArr;
    static boolean[] visited;
    static int city , bus;
    static int startCity , endCity;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        city = Integer.parseInt(br.readLine());
        bus = Integer.parseInt(br.readLine());
        StringTokenizer st;
        costArr = new int[city + 1];
        visited = new boolean[city + 1];
        for(int i = 0 ; i <= city ; i++){
            list.add(new ArrayList<Edge>());
            costArr[i] = Integer.MAX_VALUE;
        }
        for(int i = 0 ; i < bus ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Edge(end , cost));
        }
        st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());

        solution();
        System.out.println(costArr[endCity]);
//        for(int value : costArr) System.out.println(value);
    }

    public static void solution(){
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(startCity , 0));
        costArr[startCity] = 0;
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            int city = edge.city;
            int cost = edge.cost;
            if(costArr[city] < cost) continue;
            for(Edge moveEdge : list.get(city)) {
                if(costArr[moveEdge.city] > costArr[city] + moveEdge.cost) {
                    costArr[moveEdge.city] = costArr[city] + moveEdge.cost;
                    queue.offer(new Edge(moveEdge.city , costArr[moveEdge.city]));
                }
            }
        }
    }

    public static class Edge implements Comparable<Edge>{
        int city;
        int cost;
        public Edge(int city , int cost) {
            this.city = city;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
        @Override
        public String toString() {
            return "Edge [city=" + city + ", cost=" + cost + "]";
        }
    }
}