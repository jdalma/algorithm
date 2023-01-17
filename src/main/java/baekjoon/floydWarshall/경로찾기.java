package baekjoon.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 경로찾기 {
    static int[][] map;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        checked = new boolean[size];
        for(int i = 0 ; i < size ; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0 ; j < line.length ; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        for(int i = 0 ; i < size ; i++){
            for(int k = 0 ; k < size ; k++) checked[k] = false;
            for(int j = 0 ; j < size ; j++){
                if(map[i][j] == 1 && !checked[j]){
                    dfs(i , j , size);
                }
            }
        }

        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void dfs(int x , int y , int size){
        checked[y] = true;
        map[x][y] = 1;
        for(int i = 0 ; i < size ; i++){
            if(!checked[i] && map[y][i] == 1) {
//                System.out.println(map[y][i] + "  " + y + "  " +  i);
                dfs(x , i , size);
            }
        }
    }
}