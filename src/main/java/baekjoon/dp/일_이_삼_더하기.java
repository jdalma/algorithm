package baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * https://www.acmicpc.net/problem/9095
 * 참고 https://fbtmdwhd33.tistory.com/73
 */

public class 일_이_삼_더하기 {
    static int[] mem;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < size ; i++){
            int value = Integer.parseInt(br.readLine());
            mem = new int[12];
            mem[1] = 1;
            mem[2] = 2;
            mem[3] = 4;
            sb.append(solution(value)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int solution(int target){
        for(int i = 4 ; i <= target ; i++){
            mem[i] = mem[i - 3] + mem[i - 2] + mem[i - 1];
        }
        return mem[target];
    }
}
