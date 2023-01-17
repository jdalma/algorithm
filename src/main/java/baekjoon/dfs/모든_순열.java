package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 모든_순열 {
    static int[] numberArr;
    static int[] printArr;
    static int[] targetArr;
    static boolean[] checked;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        numberArr = new int[size];
        printArr = new int[size];
        targetArr = new int[size];
        checked = new boolean[size];
        for(int i = 0 ; i < size ; i++){
            numberArr[i] = i + 1;
        }
        dfs(0);
        System.out.println(result.toString());
    }

    public static void dfs(int depth){
        if(depth == numberArr.length){
            for(int value : printArr) result.append(value).append(" ");
            result.append("\n");
        }
        else if(depth < numberArr.length){
            for(int i = 0 ; i < numberArr.length ; i++){
                if(!checked[i]){
                    checked[i] = true;
                    printArr[depth] = numberArr[i];
                    dfs(depth + 1);
                    checked[i] = false;
                }
            }
        }
    }
}