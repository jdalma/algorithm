package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 숨바꼭질3 {
    static int[] move = {-1 , 1 , 2};
    static int[] distance;
    static int start , destination;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        distance = new int[100001];
        Arrays.fill(distance , Integer.MAX_VALUE);
        bfs();
        System.out.println(distance[destination]);
    }

    public static void bfs(){
        PriorityQueue<Move> queue = new PriorityQueue<Move>();
        queue.offer(new Move(start , 0));
        distance[start] = 0;
        while(!queue.isEmpty()){
            Move now = queue.poll();
            for(int i = 0 ; i <= 2 ; i++){
                int movePos = i == 2 ? now.position * move[i] : now.position + move[i];
                int moveCnt = i == 2 ? now.cost : now.cost + 1;
                if(movePos >= 0 && movePos < 100001 && distance[movePos] > moveCnt){
                    distance[movePos] = moveCnt;
                    queue.offer(new Move(movePos , moveCnt));
                }
            }
        }
    }

    private static class Move implements Comparable<Move>{
        int position;
        int cost;
        public Move(int position, int cost) {
            this.position = position;
            this.cost = cost;
        }
        @Override
        public int compareTo(Move o) {
            return this.cost - o.cost;
        }
    }
}