package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 양치기_꿍 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static char[][] map;
    static boolean[][] checked;
    static int row , col , sheepCount = 0 , wolfCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        checked = new boolean[row][col];
        for(int i = 0 ; i < row ; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < col ; j++){
                char ch = line[j];
                map[i][j] = ch;
                if(ch == 'k') sheepCount++;
                else if(ch == 'v') wolfCount++;
            }
        }
        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++){
                if(!checked[i][j] && map[i][j] != '#'){
                    solve(i , j);
                }
            }
        }
        System.out.println(sheepCount + " " + wolfCount);
    }
    public static void solve(int x , int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x , y});
        checked[x][y] = true;
        int areaSheepCount = 0 , areaWolfCount = 0;
        if(map[x][y] == 'k') areaSheepCount++;
        else if(map[x][y] == 'v') areaWolfCount++;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = now[0] + moveX[i];
                int moveYpos = now[1] + moveY[i];
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < row && moveYpos < col){
                    if(!checked[moveXpos][moveYpos] && map[moveXpos][moveYpos] != '#'){
                        checked[moveXpos][moveYpos] = true;
                        char ch = map[moveXpos][moveYpos];
                        if(ch == 'k') areaSheepCount++;
                        else if(ch == 'v') areaWolfCount++;
                        queue.offer(new int[] {moveXpos , moveYpos});
                    }
                }
            }
        }
        if(areaSheepCount > areaWolfCount) wolfCount -= areaWolfCount;
        else sheepCount -= areaSheepCount;
    }
}