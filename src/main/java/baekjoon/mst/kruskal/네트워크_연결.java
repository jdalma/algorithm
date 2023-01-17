package baekjoon.mst.kruskal;

import java.io.*;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class 네트워크_연결 {

    public static int Find(int node){
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if(relation[node] == node) return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    public static int Union(Edge edge){
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
    static List<Edge> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        relation = new int[nodeCount + 1];
        // 관계 배열 초기화
        for(int i = 1 ; i < relation.length ; i++) relation[i] = i;

        // 트리 높이 배열 생성
        rank = new int[nodeCount + 1];

        for(int i = 0 ; i < edgeCount ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Edge(startNode , endNode , cost));
        }

        Collections.sort(list);

        int result = 0 ;

        for(Edge edge : list){
            result += Union(edge);
        }
        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    static class Edge implements Comparable<Edge>{
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
