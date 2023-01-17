package baekjoon.mst.kruskal;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class 집합의_표현 {

    public static int Find(int node){
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if(relation[node] == node) return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    public static void Union(int startNode , int endNode){
        int startNodeRoot = Find(startNode);
        int endNodeRoot = Find(endNode);

        if(startNodeRoot == endNodeRoot) return;

        if(rank[startNodeRoot] < rank[endNodeRoot]){
            relation[startNodeRoot] = endNodeRoot;
        }
        else{
            relation[endNodeRoot] = startNodeRoot;
            if(rank[startNodeRoot] == rank[endNodeRoot]){
                rank[startNodeRoot]++;
            }
        }
    }

    static int[] relation;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int inputNumberSize = Integer.parseInt(st.nextToken());
        int inputLineCount = Integer.parseInt(st.nextToken());

        relation = new int[inputNumberSize + 1];
        // 관계 배열 초기화
        for(int i = 1 ; i < relation.length ; i++) relation[i] = i;

        // 트리 높이 배열 생성
        rank = new int[inputNumberSize + 1];

        for(int i = 0 ; i < inputLineCount ; i++){
            st = new StringTokenizer(br.readLine());
            boolean outputFlag = st.nextToken().equals("0") ? false : true;
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            // 입력
            if(!outputFlag){
                Union(startNode , endNode);
            }
            // 출력
            else{
                int startNodeRoot = Find(startNode);
                int endNodeRoot = Find(endNode);
                bw.write(startNodeRoot == endNodeRoot ? "YES\n" : "NO\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
