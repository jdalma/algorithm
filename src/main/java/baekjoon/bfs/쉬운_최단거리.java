package baekjoon.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


class 쉬운_최단거리 {

    static int row , col , startX , startY;
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = br.readLine().split(" ");
        row = Integer.parseInt(line[0]);
        col = Integer.parseInt(line[1]);

        int[][] map = new int[row][col];
        int[][] memo = new int[row][col];

        for(int i = 0 ; i < row ; i++){
            line = br.readLine().split(" ");
            for(int j = 0 ; j < col ; j++){
                int cell = Integer.parseInt(line[j]);
                if(cell == 2){
                    startX = i;
                    startY = j;
                }
                memo[i][j] = cell == 0 ? 0 : -1;
                map[i][j] = cell;
            }
        }

        bfs(map , memo);

        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++){
                bw.write(memo[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void bfs(int[][] map , int[][] memo){
        Queue<int[]> queue = new LinkedList<>();
        memo[startX][startY] = 0;
        queue.offer(new int[] {startX , startY});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowTime = memo[nowX][nowY];
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = nowX + moveX[i];
                int moveYpos = nowY + moveY[i];
                if(moveXpos >= 0 && moveXpos < row && moveYpos >= 0 && moveYpos < col){
                    if(map[moveXpos][moveYpos] == 1 && memo[moveXpos][moveYpos] == -1){
                        memo[moveXpos][moveYpos] = nowTime + 1;
                        queue.offer(new int[] {moveXpos , moveYpos});
                    }
                }
            }
        }
    }
}