package baekjoon.mst.kruskal;

import java.io.*;
import java.util.*;

class 트리 {
    static boolean[] isCycle , isCounted;
    static int[] relation;
    static StringBuilder sb = new StringBuilder();
    static int testCase = 0;
    static int nodeCount;
    static int edgeCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            testCase++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodeCount = Integer.parseInt(st.nextToken());
            edgeCount = Integer.parseInt(st.nextToken());

            if(nodeCount == 0 && edgeCount == 0) break;

            relation = new int[nodeCount + 1];
            isCycle = new boolean[nodeCount + 1];
            isCounted = new boolean[nodeCount + 1];

            for(int i = 1 ; i<= nodeCount ; i++) relation[i] = i;

            for(int i = 0 ; i < edgeCount ; i++){
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                Union(node1 , node2);
            }

            int treeCount = 0;
            for(int i = 1 ; i <= nodeCount ; i++){
                int root = Find(i);
//                System.out.println("node : " + i + " root : " + root + " : " + isCycle[i] + " " + isCycle[root]);
                if(!isCycle[root] && !isCycle[i]){
                    if(!isCounted[root]){
                        isCounted[root] = true;
                        treeCount++;
                    }
                }
            }
            sbAdd(treeCount);
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int Find(int node){
        if(relation[node] == node) return node;
        return relation[node] = Find(relation[node]);
    }

    public static void Union(int node1, int node2){
        if(node1 == node2) {
            isCycle[node1] = true;
            return;
        }
        int node1root = Find(node1);
        int node2root = Find(node2);

        if(node1root != node2root){
            if((isCycle[node1root] || isCycle[node2root])){
                isCycle[node1root] = true;
                isCycle[node2root] = true;
            }
            relation[node1root] = node2root;
        }
        else{
            isCycle[node1root] = true;
        }
    }



    public static void sbAdd(int treeCount){
        sb.append("Case " + testCase + ": ");
        if(treeCount == 0) sb.append("No trees.\n");
        else if(treeCount == 1) sb.append("There is one tree.\n");
        else sb.append("A forest of " + treeCount + " trees.\n");
    }
}