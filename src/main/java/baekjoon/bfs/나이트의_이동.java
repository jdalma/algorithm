package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 나이트의_이동 {
    static int[] startPos = new int[2];
    static int[] destiPos = new int[2];
    static int[] moveX = {-2 , -1 , 1 , 2 , 2 , 1 , -1 , -2};
    static int[] moveY = {1 , 2 , 2 , 1 , -1 , -2 , -2 , -1};
    static int[][] map;
    static boolean[][] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        for(int count = 0 ; count < size ; count++){
            int mapSize = Integer.parseInt(br.readLine());
            map = new int[mapSize][mapSize];
            checked = new boolean[mapSize][mapSize];
            String[] startRow = br.readLine().split(" ");
            startPos[0] = Integer.parseInt(startRow[0]);
            startPos[1] = Integer.parseInt(startRow[1]);
            String[] endRow = br.readLine().split(" ");
            destiPos[0] = Integer.parseInt(endRow[0]);
            destiPos[1] = Integer.parseInt(endRow[1]);
            bfs();
        }
    }
    public static void bfs(){
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{startPos[0] , startPos[1] , 0});
        checked[startPos[0]][startPos[1]] = true;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowCount = now[2];
            if(nowX == destiPos[0] && nowY == destiPos[1]){
                System.out.println(nowCount);
                break;
            }
            else{
                for(int i = 0 ; i < moveX.length ; i++){
                    int moveXpos = nowX + moveX[i];
                    int moveYpos = nowY + moveY[i];
                    if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < map.length && moveYpos < map.length){
                        if(!checked[moveXpos][moveYpos]){
                            checked[moveXpos][moveYpos] = true;
                            queue.offer(new int[]{moveXpos , moveYpos , nowCount + 1});
                        }
                    }
                }
            }
        }
    }
}