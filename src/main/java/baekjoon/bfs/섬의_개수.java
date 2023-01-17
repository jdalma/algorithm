package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 섬의_개수 {
    static int[] moveX = new int[] {-1 , -1 , 0 , 1 , 1 , 1 , 0 , -1};
    static int[] moveY = new int[] {0 , 1 , 1 , 1 , 0 , -1 , -1 , -1};
    static int[][] map;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            if(width == 0 && height == 0){
                break;
            }
            result = 0;
            map = new int[height][width];
            for(int i = 0 ; i < height ; i++){
                String[] line = br.readLine().split(" ");
                for(int j = 0 ; j < width ; j++){
                    map[i][j] = Integer.parseInt(line[j]);
                }
            }
            for(int i = 0 ; i < height ; i++){
                for(int j = 0 ; j < width ; j++){
                    if(map[i][j] == 1){
                        result++;
                        bfs(i , j , height , width);
                    }
                }
            }
            System.out.println(result);
        }
    }
    public static void bfs(int x , int y , int height , int width){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x , y});
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            for(int i = 0 ; i < 8 ; i++){
                int moveXpos = nowX + moveX[i];
                int moveYpos = nowY + moveY[i];
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < height && moveYpos < width && map[moveXpos][moveYpos] == 1){
                    map[moveXpos][moveYpos] = 0;
                    queue.offer(new int[] {moveXpos , moveYpos});
                }
            }
        }
    }
}