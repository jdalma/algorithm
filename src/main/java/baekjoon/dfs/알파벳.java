package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 알파벳 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int R , C;
    static boolean[] visited = new boolean[26];
    static int maxResult = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 세로
        C = Integer.parseInt(st.nextToken()); // 가로
        char[][] charArr = new char[R][C];
        for(int i = 0 ; i < R ; i++){
            char[] tmpArr = br.readLine().toCharArray();
            for(int j = 0 ; j < C ; j++){
                charArr[i][j] = (char) (tmpArr[j] - 'A');
            }
        }
        visited[charArr[0][0]] = true;
        recursive(charArr , 0 , 0 , 1);
        System.out.println(maxResult);
    }

    public static void recursive(char[][] charArr , int x , int y , int count){
        for(int i = 0 ; i < 4 ; i++){
            int moveXpos = x + moveX[i];
            int moveYpos = y + moveY[i];
            if(moveXpos >= 0 && moveXpos < R && moveYpos >= 0 && moveYpos < C){
                if(!visited[charArr[moveXpos][moveYpos]]){
                    visited[charArr[moveXpos][moveYpos]] = true;
                    recursive(charArr , moveXpos , moveYpos , count + 1);
                    visited[charArr[moveXpos][moveYpos]] = false;
                }
                else maxResult = Math.max(maxResult , count);
            }
        }
    }
}