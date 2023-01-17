package baekjoon.mst.prim;

import java.util.*;

class 섬_연결하기 {

    int[] relation;
    public int solution(int n, int[][] costs) {
        List<List<Edge>> list = new ArrayList<>();
        relation = new int[n];
        int startNode = 0;
        for(int i = 0 ; i < n ; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0 ; i < costs.length ; i++) {
            int[] line = costs[i];
            list.get(line[0]).add(new Edge(line[1] , line[2]));
            list.get(line[1]).add(new Edge(line[0] , line[2]));
            if(startNode == 0) startNode = line[0];
        }

        return prim(list , startNode);
    }

    public int prim(List<List<Edge>> list , int startNode){
        int result = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(startNode , 0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();
            int nowNode = now.node;
            // 현재 문제에서는 연결 되지 않는 섬이 없다는 전제이기에
            // 아래의 조건이 가능하다.
            if(relation[nowNode] != startNode){
                result += now.cost;
                for(Edge edge : list.get(nowNode)){
                    pq.offer(edge);
                }
                relation[nowNode] = startNode;
            }
        }

        return result;
    }

    class Edge implements Comparable<Edge>{
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}