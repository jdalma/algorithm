package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class 최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < size ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            System.out.println(least(A , B));
        }
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