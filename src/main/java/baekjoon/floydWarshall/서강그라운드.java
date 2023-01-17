package baekjoon.floydWarshall;

import java.io.*;
import java.util.*;

class 서강그라운드 {
    static int[][] dist;
    static int[] nodeItemArr;
    static int nodeCount , searchScope , edgeCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCount = Integer.parseInt(st.nextToken());
        searchScope = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        nodeItemArr = new int[nodeCount + 1];
        dist = new int[nodeCount + 1][nodeCount + 1];

        // 각 구역의 아이템 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= nodeCount ; i++) {
            if (i != 0) nodeItemArr[i] = Integer.parseInt(st.nextToken());
            // dist 배열 초기화
            for(int j = 0 ; j <= nodeCount ; j++){
                dist[i][j] = i == j ? 0 : 16;
            }
        }

        // 간선
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = cost;
            dist[end][start] = cost;
        }

        // 플로이드 와샬
        // k - 거쳐가는 노드
        for(int k = 1 ; k <= nodeCount ; k++){
            // i - 출발 노드
            for(int i = 1 ; i <= nodeCount ; i++){
                // j - 도착 노드
                for(int j = 1 ; j <= nodeCount ; j++){
                    dist[i][j] = Math.min(dist[i][j] , dist[i][k] + dist[k][j]);
                }
            }
        }

        int result = 0;
        // 아이템 합계 구하기
        for(int i = 1 ; i <= nodeCount ; i++){
            int itemSum = 0;
            for(int j = 1 ; j <= nodeCount ; j++){
                if(dist[i][j] <= searchScope) itemSum += nodeItemArr[j];
            }
            result = Math.max(result , itemSum);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
