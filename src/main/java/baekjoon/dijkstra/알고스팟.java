package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 알고스팟 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        map = new int[x][y];
        for(int i = 0 ; i < x ; i++){
            String[] line = br.readLine().split("");
            for(int j = 0 ; j < y ; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        System.out.println(start(x - 1, y - 1));
    }

    public static int start(int targetX , int targetY){
        boolean[][] visited = new boolean[targetX + 1][targetY + 1];
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(0 , 0 ,0));
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Position now = queue.poll();
            if(now.x == targetX && now.y == targetY){
                return now.breakWall;
            }
            for (int i = 0; i < 4; i++) {
                int moveXpos = now.x + moveX[i];
                int moveYpos = now.y + moveY[i];
                if (moveXpos >= 0 && moveYpos >= 0 && moveXpos <= targetX && moveYpos <= targetY && !visited[moveXpos][moveYpos]) {
                    visited[moveXpos][moveYpos] = true;
                    if (map[moveXpos][moveYpos] == 1) {
                        queue.offer(new Position(moveXpos, moveYpos, now.breakWall + 1));
                    }
                    else {
                        queue.offer(new Position(moveXpos, moveYpos, now.breakWall));
                    }
                }
            }
        }
        return 0;
    }

    private static class Position implements Comparable<Position>{
        int x;
        int y;
        int breakWall;
        public Position(int x, int y, int breakWall) {
            this.x = x;
            this.y = y;
            this.breakWall = breakWall;
        }

        @Override
        public int compareTo(Position o) {
            return breakWall - o.breakWall;
        }
    }
}