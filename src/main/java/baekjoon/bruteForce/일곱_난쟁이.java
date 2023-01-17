package baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 일곱_난쟁이 {
    static int[] height = new int[9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sumHeight = 0;
        for(int i = 0 ; i < 9 ; i++){
            int value = Integer.parseInt(br.readLine());
            sumHeight += value;
            height[i] = value;
        }

        int exceptIndex1 = 0 , exceptIndex2 = 0;
        Arrays.sort(height);
        Loop1:
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(sumHeight - (height[i] + height[j]) == 100 && i != j){
                    exceptIndex1 = i;
                    exceptIndex2 = j;
                    break Loop1;
                }
            }
        }
        for(int i = 0 ; i < 9 ; i++){
            if(exceptIndex1 != i && exceptIndex2 != i){
                System.out.println(height[i]);
            }
        }
    }
}