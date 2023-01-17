package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 소수_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < size ; i++){
            int number = Integer.parseInt(st.nextToken());
            if(isPrime(number)) count++;
        }
        System.out.println(count);
    }

    public static boolean isPrime(int num){
        if(num == 1) return false;
        for(int i = 2 ; i < num ; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}