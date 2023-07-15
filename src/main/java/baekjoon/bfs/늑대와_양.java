package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 늑대와_양 {
    static char[][] pasture;
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int fenceCount = 0 , row , col;
    static boolean wolvesAroundTheSheep = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken()); // 목장의 가로 길이
        col = Integer.parseInt(st.nextToken()); // 목장의 세로 길이
        pasture = new char[row][col];
        for(int i = 0 ; i < row ; i++){
            st = new StringTokenizer(br.readLine());
            char[] line = st.nextToken().toCharArray();
            System.arraycopy(line, 0, pasture[i], 0, line.length);
        }

        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++){
                if(pasture[i][j] == 'W' && !wolvesAroundTheSheep){
                    // 늑대 주위 탐색
                    searchNearby(i , j);
                }
            }
        }

        if(wolvesAroundTheSheep){
            System.out.println(0);
        }
        else{
            System.out.println(1);
            for(int i = 0 ; i < row ; i++){
                for(int j = 0 ; j < col ; j++){
                    System.out.print(pasture[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void searchNearby(int x , int y){
        for(int i = 0 ; i < 4 ; i++){
            int moveXpos = x + moveX[i];
            int moveYpos = y + moveY[i];
            if(moveXpos >= 0 && moveXpos < row && moveYpos >= 0 && moveYpos < col){
                // 해당 위치에 아무것도 없다면 울타리 치기
                if(pasture[moveXpos][moveYpos] == '.'){
                    pasture[moveXpos][moveYpos] = 'D';
                }
                // 바로 옆에 양이 있다면 실패
                else if(pasture[moveXpos][moveYpos] == 'S'){
                    wolvesAroundTheSheep = true;
                    break;
                }
            }
        }
    }
}
