package baekjoon.mst;

import java.util.*;

class 최소스패닝트리 {
    static int[] relation;
    static List<Edge> roads;
    static int totalCost = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input1 = sc.nextInt();
        int input2 = sc.nextInt();

        roads = new ArrayList<Edge>();
        relation = new int[input1 + 1];
        // 관계 배열 초기화
        for(int i = 0 ; i < input1 + 1 ; i++) relation[i] = i;

        for(int i = 0 ; i < input2 ; i++){
            int value1 = sc.nextInt();
            int value2 = sc.nextInt();
            int cost = sc.nextInt();
            roads.add(new Edge(value1 , value2 , cost));
        }
        Collections.sort(roads);

        for(Edge edge : roads){
            Union(edge);
        }
        System.out.println(totalCost);
    }

    // 집합 관계 찾기
    public static int Find(int vertex){
//        System.out.println(vertex + " - " + relation[vertex]);
        if(vertex == relation[vertex]) return vertex;
        else return relation[vertex] = Find(relation[vertex]);
    }

    // 집합 병합
    public static void Union(Edge edge){
        int vertex1 = Find(edge.vertex1);
        int vertex2 = Find(edge.vertex2);
        if(vertex1 != vertex2) {
            totalCost += edge.cost;
            relation[vertex1] = vertex2;
        }
    }

    private static class Edge implements Comparable<Edge>{
        int vertex1;
        int vertex2;
        int cost;
        public Edge(int vertex1 , int vertex2 , int cost){
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return "Edge{" +
                    "vertex1=" + vertex1 +
                    ", vertex2=" + vertex2 +
                    ", cost=" + cost +
                    '}';
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

}