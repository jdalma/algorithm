package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 녹색_옷_입은_애가_젤다지 {
    static int size;
    static int[][] map , memo;
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int count = 0;
        while(true){
            size = Integer.parseInt(br.readLine());
            if(size == 0) break;
            map = new int[size][size];
            memo = new int[size][size];
            for(int i = 0 ; i < size ; i++){
                String[] line = br.readLine().split(" ");
                for(int j = 0 ; j < line.length ; j++){
                    map[i][j] = Integer.parseInt(line[j]);
                    memo[i][j] = Integer.MAX_VALUE;
                }
            }
            result.append("Problem ").append(++count).append(": ").append(dijkstra()).append("\n");
        }
        System.out.println(result.toString());
    }

    public static int dijkstra(){
        memo[0][0] = map[0][0];
        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.offer(new Position(0 , 0 , map[0][0]));
        while(!pq.isEmpty()){
            Position now = pq.poll();
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = now.x + moveX[i];
                int moveYpos = now.y + moveY[i];
                if(moveXpos >= 0 && moveXpos < size && moveYpos >= 0 && moveYpos < size){
                    if(memo[moveXpos][moveYpos] > now.cost + map[moveXpos][moveYpos]) {
                        memo[moveXpos][moveYpos] = now.cost + map[moveXpos][moveYpos];
                        pq.offer(new Position(moveXpos , moveYpos , now.cost + map[moveXpos][moveYpos]));
                    }
                }
            }
        }
        return memo[size - 1][size - 1];
    }

    static class Position implements Comparable<Position>{
        int x , y , cost;
        public Position(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Position o) {
            return this.cost - o.cost;
        }
    }

}
