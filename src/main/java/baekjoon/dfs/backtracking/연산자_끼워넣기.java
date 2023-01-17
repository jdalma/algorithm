package baekjoon.dfs.backtracking;

import java.util.*;
import java.io.*;

public class 연산자_끼워넣기 {
    static int[] symbolArr = new int[12];
    static int[] numArr;
    static int[] printArr;
    static boolean[] checked;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        numArr = new int[size];
        checked = new boolean[size];
        printArr = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < size ; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int index = 0;
        for(int i = 0 ; i < 4 ; i++){
            int count = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < count ; j++){
                symbolArr[index++] = i + 1;
            }
        }
        recursive(1 ,0 , numArr[0]);
        System.out.println(max + "\n" + min);
    }
    public static void recursive(int index , int count , int number) {
        if(count == numArr.length - 1) {
//            System.out.println("result = " + number);
            max = Math.max(number, max);
            min = Math.min(number, min);
        }
        else{
            for(int i = 0 ; i < numArr.length ; i++) {
                if(!checked[i]){
                    checked[i] = true;
                    switch(symbolArr[i]){
                        case 1:
                            recursive(index + 1 ,count + 1 , number + numArr[index]);
//                            System.out.print("+");
                            break;
                        case 2:
                            recursive(index + 1 ,count + 1 , number - numArr[index]);
//                            System.out.print("-");
                            break;
                        case 3:
                            recursive(index + 1 ,count + 1 , number * numArr[index]);
//                            System.out.print("*");
                            break;
                        case 4:
                            recursive(index + 1 ,count + 1 , number / numArr[index]);
//                            System.out.print("/");
                            break;
                    }
                    checked[i] = false;
                }
            }
        }
    }
}