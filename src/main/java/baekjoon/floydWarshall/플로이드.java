package baekjoon.floydWarshall;

import java.io.*;
import java.util.*;

class 플로이드 {
    static int[][] dist;
    static final int INF = 100000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        dist = new int[nodeCount][nodeCount];

        // 배열 초기화
        for(int i = 0 ; i < nodeCount ; i++){
            for(int j = 0 ; j < nodeCount ; j++){
                dist[i][j] = i == j ? 0 : INF;
            }
        }

        // 간선
        for(int i = 0 ; i < edgeCount ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            int min = Math.min(dist[start][end] , cost);
            dist[start][end] = min;
        }

        // 플로이드
        for(int k = 0 ; k < nodeCount ; k++){
            for(int i = 0 ; i < nodeCount ; i++){
                for(int j = 0 ; j < nodeCount ; j++){
                    if(i != j) dist[i][j] = Math.min(dist[i][j] , dist[i][k] + dist[k][j]);
                }
            }
        }

        // 출력
        for(int i = 0 ; i < nodeCount ; i++){
            for(int j = 0 ; j < nodeCount ; j++){
                bw.append(dist[i][j] >= INF ? "0 " : dist[i][j] + " ");
            }
            bw.append("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
