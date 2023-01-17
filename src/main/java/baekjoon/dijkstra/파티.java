package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 - `단일 출발(single-source) 최단경로` 와 `단일 도착(single-destination) 최단경로` 구분하는 문제
 - `int[] result1 = dijkstra(destination , list);` ➜ `단일 출발(single-source) 최단경로`
 - `int[] result2 = dijkstra(destination , reverseList);` ➜ `단일 도착(single-destination) 최단경로`
 */

class 파티 {
    static int city , edge, destination;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<List<Edge>> list = new ArrayList<>();
        List<List<Edge>> reverseList = new ArrayList<>();
        city = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i <= city ; i++) {
            list.add(new ArrayList<Edge>());
            reverseList.add(new ArrayList<Edge>());
        }
        for(int i = 0 ; i < edge ; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(node1).add(new Edge(node2 , cost));
            reverseList.get(node2).add(new Edge(node1 , cost));
        }

        // 모든 도시
        int[] result1 = dijkstra(destination , list);
        int[] result2 = dijkstra(destination , reverseList);

        int maxCostToGoToCome = Integer.MIN_VALUE;

        for(int i = 1 ; i <= city ; i++){
            if(result1[i] != Integer.MAX_VALUE && result2[i] != Integer.MAX_VALUE){
                maxCostToGoToCome = Math.max(maxCostToGoToCome , result1[i]+ result2[i]);
            }
        }

        System.out.println(maxCostToGoToCome);
    }

    public static int[] dijkstra(int startCity ,  List<List<Edge>> list){
        int[] costArr = new int[city + 1];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(startCity , 0));
        costArr[startCity] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();
            int city = now.city;
            int cost = now.cost;
            for(Edge nearEdge : list.get(city)){
                int sumCost = cost + nearEdge.cost;
                if(costArr[nearEdge.city] > sumCost){
                    costArr[nearEdge.city] = sumCost;
                    pq.offer(new Edge(nearEdge.city , sumCost));
                }
            }
        }
        return costArr;
    }

    static class Edge implements Comparable<Edge>{
        int city;
        int cost;
        public Edge(int city, int cost) {
            super();
            this.city = city;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return o.cost - this.cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "city=" + city +
                    ", cost=" + cost +
                    '}';
        }
    }
}