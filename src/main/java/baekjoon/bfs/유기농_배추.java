package baekjoon.bfs;

import java.io.*;
import java.util.*;


public class 유기농_배추 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int[][] map;
    static int resultCount;
    static int x , y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int test = 0 ; test < testCase ; test++) {
            resultCount = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            map = new int[x][y];
            for(int j = 0 ; j < count ; j++) {
                st = new StringTokenizer(br.readLine());
                int posX = Integer.parseInt(st.nextToken());
                int posY = Integer.parseInt(st.nextToken());
                map[posX][posY] = 1;
            }
            for(int i = 0 ; i < x ; i++) {
                for(int j = 0 ; j < y ; j++) {
                    if(map[i][j] == 1) {
                        map[i][j] = 0;
                        resultCount++;
                        bfs(i , j);
                    }
                }
            }
            System.out.println(resultCount);
        }
    }

    public static void bfs(int locX , int locY) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {locX , locY});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            for(int i = 0 ; i < 4 ; i++) {
                int moveXpos = nowX + moveX[i];
                int moveYpos = nowY + moveY[i];
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < x && moveYpos < y && map[moveXpos][moveYpos] == 1) {
                    map[moveXpos][moveYpos] = 0;
                    queue.offer(new int[] {moveXpos , moveYpos});
                }
            }
        }
    }
}