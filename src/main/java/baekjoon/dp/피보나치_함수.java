package baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class 피보나치_함수 {
    static int[] mem;
    static int[][] dp;
    static int zeroCount, oneCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < size ; i++){
            zeroCount = 0 ; oneCount = 0;
            int value = Integer.parseInt(br.readLine());
            mem = new int[41];
            // recursive(value);
            useCount(value);
            sb.append(zeroCount).append(" ").append(oneCount).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int recursive(int value){
        if(value == 0) return mem[value] = 0;
        else if(value == 1) return mem[value] = 1;
        else if(mem[value] != 0) return mem[value];
        else{
            return mem[value] = recursive(value - 1) + recursive(value - 2);
        }
    }

    public static void useCount(int target){
        dp = new int[2][41];
        dp[0][0] = 1;
        dp[1][1] = 1;
        for(int i = 2 ; i <= target ; i++){
            dp[0][i] = dp[0][i - 2] + dp[0][i - 1];
            dp[1][i] = dp[1][i - 2] + dp[1][i - 1];
        }
        zeroCount = dp[0][target];
        oneCount = dp[1][target];
    }
}