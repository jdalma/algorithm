package baekjoon.mst.kruskal;

import java.util.*;

class 섬_연결하기 {
    int[] relation;
    int[] rank;
    public int solution(int n, int[][] costs) {
        List<Edge> list = new ArrayList<>();
        relation = new int[n];
        rank = new int[n];

        // make-set 관계 배열 초기화
        for(int i = 0 ; i < n ; i++) relation[i] = i;


        for(int i = 0 ; i < costs.length ; i++) {
            int[] line = costs[i];
            list.add(new Edge(line[0] , line[1] , line[2]));
        }

        // Edge 객체 비용 오름차순 정렬
        Collections.sort(list);

        int answer = 0;

        for(Edge edge : list){
            answer += Union(edge);
        }

        return answer;
    }

    public int Union(Edge edge){
        int startNodeRoot = Find(edge.startNode);
        int endNodeRoot = Find(edge.endNode);

        // 두 값의 부모가 같다면 이미 같은 트리
        if(startNodeRoot == endNodeRoot) return 0;

        // 항상 높이가 더 낮은 트리를 높이가 더 높은 트리 밑에 넣는다.
        if(rank[startNodeRoot] < rank[endNodeRoot]){
            relation[endNodeRoot] = startNodeRoot;
        }
        else{
            relation[startNodeRoot] = endNodeRoot;
            // 높이가 같다면 startNodeRoot에 추가 후 해당 높이를 증가시킨다.
            if(rank[startNodeRoot] == rank[endNodeRoot]){
                rank[startNodeRoot]++;
            }
        }
        return edge.cost;
    }

    public int Find(int node){
        if(node == relation[node])
            return node;
        // 경로 압축
        return relation[node] = Find(relation[node]);
    }

    class Edge implements Comparable<Edge>{
        int startNode;
        int endNode;
        int cost;

        public Edge(int startNode, int endNode, int cost) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}