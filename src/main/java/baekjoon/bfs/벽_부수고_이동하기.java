package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 필독 https://www.acmicpc.net/board/view/27386
 *
 * - `memoization` 실패!!!
 * - 3차원 배열로 벽을 부순 상태까지 따로 방문 체크 하여야 했다.
 */

class 벽_부수고_이동하기 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static char[][] map;
    static boolean[][][] visited;
    static int row , col;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        visited = new boolean[row][col][2];
        for(int i = 0 ; i < row ; i++) map[i] = br.readLine().toCharArray();
        bfs();
        if(result == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(result);
    }

    public static void bfs() {
        Queue<Move> queue = new LinkedList<Move>();
        queue.offer(new Move(0 , 0 , 1 , 0));
        visited[0][0][0] = true;
        while(!queue.isEmpty()){
            Move now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            if(nowX == row - 1 && nowY == col - 1){
                result = Math.min(result , now.time);
            }
            for(int i = 0 ; i < 4 ; i++) {
                int moveXpos = nowX + moveX[i];
                int moveYpos = nowY + moveY[i];
                int time = now.time + 1;
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < row && moveYpos < col && !visited[moveXpos][moveYpos][now.breakWall]) {
                    if(map[moveXpos][moveYpos] == '0') {
                        queue.offer(new Move(moveXpos , moveYpos , time , now.breakWall));
                        visited[moveXpos][moveYpos][now.breakWall] = true;
                    }
                    else if(map[moveXpos][moveYpos] == '1' && now.breakWall == 0){
                        queue.offer(new Move(moveXpos , moveYpos , time , 1));
                        visited[moveXpos][moveYpos][1] = true;
                    }
                }
            }
        }
    }

    private static class Move{
        int x;
        int y;
        int time;
        int breakWall;
        public Move(int x, int y, int time, int breakWall) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.breakWall = breakWall;
        }
    }
}