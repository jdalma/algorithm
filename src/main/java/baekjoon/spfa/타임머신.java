package baekjoon.spfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 타임머신 {
    static List<List<Move>> list = new ArrayList<>();
    static int vertex , edge;
    static long costArr[];
    static boolean checked[];
    static int cycleCount[];
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertex = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        costArr = new long[vertex + 1];
        checked = new boolean[vertex + 1];
        cycleCount = new int[vertex + 1];
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

        System.out.println(sb.toString());
    }

    public static boolean solution() {
        costArr[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        cycleCount[1]++;
        checked[1] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            checked[now] = false;
            for(Move move : list.get(now)) {
                int endVertex = move.end;
                long cost = move.cost;
                if(costArr[endVertex] > costArr[now] + cost){
                    costArr[endVertex] = costArr[now] + cost;
                    if(!checked[endVertex]){
                        // cycleCount는 해당 노드의 접근 횟수를 카운트 하며
                        // 한 노드의 최대 접근 횟수는 vertex - 1 만큼이다
                        // vertex - 1 보다 크게 접근햇다면 음의 사이클이 발생한 것
                        if(cycleCount[endVertex]++ > vertex){
                            return false;
                        }
                        queue.offer(endVertex);
                        checked[endVertex] = true;
                    }
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