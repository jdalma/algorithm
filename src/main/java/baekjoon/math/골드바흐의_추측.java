package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 골드바흐의_추측 {
    static StringBuilder resultString = new StringBuilder();
    static boolean[] memoization = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            int number = Integer.parseInt(br.readLine());
            if(number == 0) break;
            else{
                solve(number);
            }
        }
        System.out.println(resultString);
    }
    public static boolean solve(int number){
        for(int i = 3 ; i < number ; i++){
            if(number == (i + number - i) && isPrime(i) && isPrime(number - i)){
                resultString.append(number).append(" = ").append(i).append(" + ").append(number - i).append("\n");
                return true;
            }
        }
        resultString.append("Goldbach's conjecture is wrong.");
        return false;
    }
    public static boolean isPrime(int num){
        if(memoization[num]) return true;
        else if(num == 1) return false;
        for(int i = 2 ; i < num ; i++){
            if(num % i == 0) return false;
        }
        return memoization[num] = true;
    }
}