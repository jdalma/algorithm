package baekjoon.bfs;

import java.io.*;
import java.util.*;

class 양 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static char[][] map;
    static boolean[][] checked;
    static List<int[]> wolfPos = new ArrayList<>();
    static int rows , cols;
    static int sheepCount = 0, wolfCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        map = new char[rows][cols];
        checked = new boolean[rows][cols];
        for(int i = 0 ; i < rows ; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < line.length ; j++){
                char ch = line[j];
                map[i][j] = ch;
                if(ch == 'o') sheepCount++;
                else if(ch == 'v') {
                    wolfPos.add(new int[] {i , j});
                    wolfCount++;
                }
            }
        }

        for(int[] pos : wolfPos){
            if(!checked[pos[0]][pos[1]]){
                bfs(pos);
            }
        }
        System.out.println(sheepCount + " " + wolfCount);
    }
    public static void bfs(int[] pos){
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(pos);
        int areaSheepCount = 0;
        int areaWolfCount = 0;
        checked[pos[0]][pos[1]] = true;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            if(map[now[0]][now[1]] == 'o') areaSheepCount++;
            else if(map[now[0]][now[1]] == 'v') areaWolfCount++;
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = now[0] + moveX[i];
                int moveYpos = now[1] + moveY[i];
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < rows && moveYpos < cols){
                    if(!checked[moveXpos][moveYpos] && map[moveXpos][moveYpos] != '#'){
                        checked[moveXpos][moveYpos] = true;
                        queue.offer(new int[] {moveXpos , moveYpos});
                    }
                }
            }
        }
        if(areaSheepCount > areaWolfCount) wolfCount -= areaWolfCount;
        else sheepCount -= areaSheepCount;
    }
}
