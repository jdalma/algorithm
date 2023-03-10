package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class 공주님을_구해라 {
    static int row , col , time , swordXPosition , swordYPosition;
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static String result = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        row = Integer.parseInt(line[0]);
        col = Integer.parseInt(line[1]);
        time = Integer.parseInt(line[2]);

        int[][] map = new int[row][col];
        int[][] memo = new int[row][col];

        for(int i = 0 ; i < row ; i++){
            line = br.readLine().split(" ");
            for(int j = 0 ; j < col ; j++){
                // 검을 찾으면 해당 x , y 위치 저장
                int cell = Integer.parseInt(line[j]);
                if(cell == 2){
                    swordXPosition = i;
                    swordYPosition = j;
                }
                // memo 배열은 최댓값으로 저장
                memo[i][j] = Integer.MAX_VALUE;
                map[i][j] = cell;
            }
        }

        bfs(map , memo);

        System.out.println(result);
    }

    public static void bfs(int[][] map , int[][] memo){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0 , 0 , 0});
        memo[0][0] = 0;
        while(!q.isEmpty()){
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowTime = memo[nowX][nowY];
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = nowX + moveX[i];
                int moveYpos = nowY + moveY[i];
                if(moveXpos >= 0 && moveXpos < row && moveYpos >= 0 && moveYpos < col){
                    int cell = map[moveXpos][moveYpos];
                    if(cell != 1 && memo[moveXpos][moveYpos] > nowTime + 1){
                        memo[moveXpos][moveYpos] = nowTime + 1;
                        q.offer(new int[] {moveXpos , moveYpos});
                    }
                }
            }
        }
        // memo[row - 1][col - 1]은 공주한테 까지 걸어간 비용이 저장 돼있다.
        // 검의 위치에서 공주의 위치를 계산하여 누적시킨다.
        if(memo[swordXPosition][swordYPosition] != Integer.MAX_VALUE){
            memo[swordXPosition][swordYPosition] += Math.abs(swordXPosition - (row - 1)) + Math.abs(swordYPosition - (col - 1));
        }
        // 검을 들고 찾은 경우와 걸어간 경우의 최소 값을 저장한다.
        int findPrincess = Math.min(memo[swordXPosition][swordYPosition] , memo[row - 1][col - 1]);
        result = findPrincess == 0 || findPrincess > time ? "Fail" : String.valueOf(findPrincess);
    }
}