package baekjoon.mst.kruskal;

import java.io.*;
import java.security.cert.CollectionCertStoreParameters;
import java.sql.Array;
import java.util.*;

class 별자리_만들기 {

    public static int Find(int node){
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if(relation[node] == node) return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    public static float Union(Edge edge){
        int startNodeRoot = Find(edge.startNode);
        int endNodeRoot = Find(edge.endNode);

        if(startNodeRoot == endNodeRoot) return 0;

        if(rank[startNodeRoot] < rank[endNodeRoot]){
            relation[startNodeRoot] = endNodeRoot;
        }
        else{
            relation[endNodeRoot] = startNodeRoot;
            if(rank[startNodeRoot] == rank[endNodeRoot]){
                rank[startNodeRoot]++;
            }
        }

        return edge.cost;
    }

    static int[] relation;
    static int[] rank;
    static List<Position> positions = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int positionCount = Integer.parseInt(br.readLine());

        // 관계 배열 초기화
        relation = new int[positionCount];
        for(int i = 0 ; i < relation.length ; i++) relation[i] = i;

        // 트리 높이 배열 생성
        rank = new int[positionCount + 1];

        for(int i = 0 ; i < positionCount ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());

            positions.add(new Position(x , y));
        }

        for(int i = 0 ; i < positionCount ; i++){
            for(int j = 0 ; j < positionCount ; j++){
                // 모든 별의 비용 계산
                if(i != j){
                    edges.add(new Edge(i , j , getDistance(positions.get(i) , positions.get(j))));
                }
            }
        }

        Collections.sort(edges);

        float result = 0f;
        for(Edge edge : edges){
            result += Union(edge);
        }

        bw.append(String.format("%.2f", result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static float getDistance(Position p1 , Position p2){
        float diffX = p1.x - p2.x;
        float diffY = p1.y - p2.y;
        return (float) Math.sqrt(Math.pow(diffX , 2) + Math.pow(diffY , 2));
    }

    static class Position{
        float x;
        float y;

        public Position(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int startNode;
        int endNode;
        float cost;

        public Edge(int startNode, int endNode , float cost) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (this.cost - o.cost);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "startNode=" + startNode +
                    ", endNode=" + endNode +
                    ", cost=" + cost +
                    '}';
        }
    }
}
