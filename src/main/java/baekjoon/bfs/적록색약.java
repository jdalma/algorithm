package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 적록색약 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static char[][] map , map2;
    static boolean[][] visited;
    static int size;
    static int colorBlindnessCount = 0 , colorCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        map = new char[size][size];
        map2 = new char[size][size];
        for(int i = 0 ; i < size ; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < size ; j++) {
                char value = line[j];
                map[i][j] = value;
                map2[i][j] = value == 'G' ? 'R' : value;
            }
        }
        for(int count = 0 ; count < 2 ; count++) {
            visited = new boolean[size][size];
            for(int i = 0 ; i < size ; i++) {
                for(int j = 0 ; j < size ; j++) {
                    if(!visited[i][j]) {
                        if(count == 0){
                            bfs(map , i , j , map[i][j] ,'N');
                        }
                        else bfs(map2 , i , j , map2[i][j] ,'Y');
                    }
                }
            }
        }
        System.out.println(colorCount + " " + colorBlindnessCount);
    }

    private static void bfs(char[][] map , int x , int y , char color , char flag) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {x , y});
        visited[x][y] = true;
//        System.out.println("[" + x + " , " + y + "] " + map[x][y] + " - " + flag);
        if(flag == 'Y') colorBlindnessCount++;
        else colorCount++;
        while(!queue.isEmpty()) {
//            System.out.println(queue.size());
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            for(int i = 0 ; i < 4 ; i++) {
                int moveXpos = nowX + moveX[i];
                int moveYpos = nowY + moveY[i];
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < size && moveYpos < size){
                    if(color == map[moveXpos][moveYpos] && !visited[moveXpos][moveYpos]) {
//                        System.out.println("[" + moveXpos + " , " + moveYpos + "] " + map[moveXpos][moveYpos]);
                        visited[moveXpos][moveYpos] = true;
                        queue.offer(new int[]{moveXpos , moveYpos});
                    }
                }
            }
        }
    }
}