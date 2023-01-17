package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class 최대공약수와_최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        System.out.println(greatest(A , B));
        System.out.println(least(A , B));
    }

    public static int greatest(int A , int B){
        List<Integer> firstList = new ArrayList<Integer>();
        for(int i = 1 ; i <= A ; i++){
            if(A % i == 0) firstList.add(i);
        }
        List<Integer> secondList = new ArrayList<Integer>();
        for(int i = 1 ; i <= B ; i++){
            if(B % i == 0) secondList.add(i);
        }
        int firstIndex = 0;
        int secondIndex = 0;
        int result = 0;
        while(true){
            if(firstIndex >= firstList.size() || secondIndex >= secondList.size()) break;
            int first = firstList.get(firstIndex);
            int second = secondList.get(secondIndex);
            if(first == second) {
                result = first;
                firstIndex++;
                secondIndex++;
            }
            else if(first > second) secondIndex++;
            else if(second > first) firstIndex++;
        }
        return result;
    }

    public static int least(int A , int B){
        int multipleA = 1;
        int multipleB = 1;
        while(true){
            int tmpA = A * multipleA;
            int tmpB = B * multipleB;
            if(tmpA == tmpB) return tmpA;
            else if(tmpA > tmpB) multipleB++;
            else if(tmpB > tmpA) multipleA++;
        }
    }
}