package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 점프게임 {
    static boolean[][] visited;
    static int[][] map;
    static int N , K;
    static boolean gameEnd = false;
    static int timeCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 칸 수
        K = Integer.parseInt(st.nextToken()); // 줄 바꿈 후 점프 칸 수
        map = new int[2][N];
        visited = new boolean[2][N];
        for(int i = 0 ; i < 2 ; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

//        for(int i = 0 ; i < 2 ; i++) {
//        	for(int j = 0 ; j < N ; j++) {
//        		System.out.print(map[i][j]);
//        	}
//        	System.out.println();
//        }
        if(bfs()) System.out.println("1");
        else System.out.println("0");
    }
    public static boolean bfs() {
//    	PriorityQueue<Position> pq = new PriorityQueue<Position>();
        Queue<Position> pq = new LinkedList<Position>();
        int[] moveDistance = {-1 , 1 , K};
        pq.offer(new Position(0 , 0 , 0));
        visited[0][0] = true;
        while(!pq.isEmpty()) {
            Position now = pq.poll();
//    		System.out.println(now);
            int moveX , moveY;
            for(int i = 0 ; i < 3 ; i++) {
                if(i == 2) moveX = now.x == 1 ? 0 : 1;
                else moveX = now.x;
                moveY = now.y + moveDistance[i];

                if((now.time > moveY)) continue;
                else if(moveY >= N) {
//					System.out.print(moveY + " ");
                    return true;
                }
                else if(moveY >= 0 && map[moveX][moveY] == 1 && !visited[moveX][moveY]) {
                    pq.offer(new Position(moveX , moveY , now.time + 1));
                    visited[moveX][moveY] = true;
                }
            }
        }
        return false;
    }

    private static class Position implements Comparable<Position>{
        int x;
        int y;
        int time;
        public Position(int x, int y , int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
        @Override
        public int compareTo(Position o) {
            return o.y - this.y;
        }
        @Override
        public String toString() {
            return "Position [x=" + x + ", y=" + y + "]";
        }

    }
}