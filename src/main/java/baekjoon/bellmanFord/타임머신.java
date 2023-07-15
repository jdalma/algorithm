package baekjoon.bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - 처음에 `edge`기준으로만 확인하면 될 줄 알았다.
 * - 하지만 **사이클이 존재할 수 있으니** 처음에 `(vertex - 1) * edge`를 확인하여야 했다.
 * - [벨만-포드 참고](https://coder-in-war.tistory.com/entry/%EA%B0%9C%EB%85%90-38-%EB%B2%A8%EB%A7%8C%ED%8F%AC%EB%93%9CBellman-Ford-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
 *   - `INF`일 때 `contiune`하는 이유
 *   - `(vertex - 1) * edge`를 확인하는 이유
 */

class 타임머신 {
    static List<List<Move>> list = new ArrayList<>();
    static int vertex , edge;
    static long[] costArr;
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertex = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        costArr = new long[vertex + 1];
        for(int i = 0 ; i <= vertex ; i++) {
            list.add(new ArrayList<Move>());
            costArr[i] = INF;
        }

        for(int i = 0 ; i < edge ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            list.get(start).add(new Move(end , cost));
        }
        StringBuilder sb = new StringBuilder();
        if(solution()) {
            for(int i = 2 ; i <= vertex ; i++) {
                sb.append(costArr[i] == INF ? "-1" : costArr[i]).append("\n");
            }
        }
        else sb.append("-1\n");

        System.out.println(sb);
    }

    public static boolean solution() {
        costArr[1] = 0;
        // 해당 3중 반복문은 (vertex - 1) * edge 만큼이다.
        for(int q = 1 ; q < vertex ; q++) {
            for(int i = 1 ; i <= vertex ; i++) {
                if(costArr[i] == INF) continue;
                for(Move move : list.get(i)) {
                    int endVertex = move.end;
                    long cost = move.cost;
                    costArr[endVertex] = Math.min(costArr[endVertex] , costArr[i] + cost);
                }
            }
        }
        for(int i = 1 ; i <= vertex ; i++) {
            for(Move move : list.get(i)) {
                int endVertex = move.end;
                long cost = move.cost;
                if(costArr[i] != INF && costArr[endVertex] > costArr[i] + cost) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class Move{
        int end;
        long cost;
        public Move(int end, long cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
