package baekjoon.bruteForce;

import java.util.*;
import java.io.*;

public class 로또 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            if(count != 0){
                int[] numberArr = new int[count];
                int[] printArr = new int[6];
                boolean[] checked = new boolean[count];
                for(int i = 0 ; i < count ; i++){
                    numberArr[i] = Integer.parseInt(st.nextToken());
                }
                dfs(numberArr , printArr , checked ,0 , 0);
                System.out.println();
            }
            else break;
        }
    }
    public static void dfs(int[] numberArr , int[] printArr , boolean[] checked , int index , int numberIndex){
        if(index == 6){
            for(int value : printArr) System.out.print(value + " ");
            System.out.println();
        }
        else{
            for(int i = numberIndex ; i < numberArr.length; i++){
                if(!checked[i]){
                    checked[i] = true;
                    printArr[index] = numberArr[i];
                    dfs(numberArr , printArr , checked , index + 1 , i + 1);
                    checked[i] = false;
                }
            }
        }
    }
}