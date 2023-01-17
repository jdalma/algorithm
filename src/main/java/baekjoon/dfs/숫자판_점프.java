package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 숫자판_점프 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int map[][] = new int[5][5];
    static Set<Integer> resultSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0 ; i < 5 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 5 ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                dfs(i , j , map[i][j] , 0);
            }
        }

        System.out.println(resultSet.size());
    }

    public static void dfs(int x , int y , int sum , int move){
        if(move == 5){
            resultSet.add(sum);
        }
        else{
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = x + moveX[i];
                int moveYpos = y + moveY[i];
                if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < 5 && moveYpos < 5){
                    dfs(moveXpos , moveYpos , (sum * 10) + map[moveXpos][moveYpos] , move + 1);
                }
            }
        }
    }
}
