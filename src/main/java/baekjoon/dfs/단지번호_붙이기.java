package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 단지번호_붙이기 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int size;
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        int[][] arr = new int[size][size];
        for(int i = 0 ; i < size ; i++){
            String[] line = br.readLine().split("");
            for(int j = 0 ; j < size ; j++){
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        List<Integer> resultList = new ArrayList<Integer>();
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                if(arr[i][j] == 1){
                    arr[i][j] = 2;
                    resultList.add(solution(arr , i , j , 1));
                }
            }
        }
        Collections.sort(resultList);
        System.out.println(resultList.size());
        for(int value : resultList){
            System.out.println(value);
        }
    }

    public static int solution(int[][] arr , int x , int y , int count){
//        System.out.println(x + " " + y + " " + count);
        for(int i = 0 ; i < 4 ; i++){
            int moveXpos = x + moveX[i];
            int moveYpos = y + moveY[i];
            if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < size && moveYpos < size){
                if(arr[moveXpos][moveYpos] == 1){
                    arr[moveXpos][moveYpos] = 2;
                    count = solution(arr , moveXpos , moveYpos , count + 1);
                }
            }
        }
        return count;
    }
}