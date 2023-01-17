package baekjoon.bfs;

import java.util.*;
import java.io.*;

public class 미로탐색 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int[][] maze;
    static boolean[][] checked;
    static int x , y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        maze = new int[x][y];
        checked = new boolean[x][y];
        for(int i = 0 ; i < x ; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0 ; j < y ; j++) {
                maze[i][j] = Integer.parseInt(line[j]);
            }
        }
        checked[0][0] = true;
        bfs();
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {0 , 0 , 1});
        while(!queue.isEmpty()) {
            int[] nowArr = queue.poll();
            int nowX = nowArr[0];
            int nowY = nowArr[1];
            int moveCount = nowArr[2];
            if(nowX == x - 1 && nowY == y - 1) {
                System.out.println(moveCount);
                System.exit(0);
            }
            else {
                for(int i = 0 ; i < 4 ; i++) {
                    int moveXpos = nowX + moveX[i];
                    int moveYpos = nowY + moveY[i];
                    if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < x && moveYpos < y && maze[moveXpos][moveYpos] == 1 && !checked[moveXpos][moveYpos]){
                        checked[moveXpos][moveYpos] = true;
                        queue.offer(new int[] {moveXpos , moveYpos , moveCount + 1});
                    }
                }
            }
        }
    }
}