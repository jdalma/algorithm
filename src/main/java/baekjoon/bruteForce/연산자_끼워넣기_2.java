package baekjoon.bruteForce;

import java.util.*;
import java.io.*;

public class 연산자_끼워넣기_2 {
    static int[] symbolArr = new int[4];
    static int[] numArr;
    static int[] printArr;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int symbolCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        numArr = new int[size];
        printArr = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < size ; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int index = 0;
        for(int i = 0 ; i < 4 ; i++){
            int count = Integer.parseInt(st.nextToken());
            symbolArr[i] = count;
            symbolCount += count;
        }

//        for(int value : symbolArr) System.out.print(value + " ");

        recursive(1 , numArr[0]);
        System.out.println(max + "\n" + min);
    }
    private static void recursive(int index, int number) {
        if(index == numArr.length) {
//            System.out.println("result = " + number);
            max = Math.max(number, max);
            min = Math.min(number, min);
        }
        else{
            for(int i = 0 ; i < 4 ; i++) {
                if(symbolArr[i] == 0) continue;
                symbolArr[i] -= 1;
                switch(i){
                    case 0:
                        recursive(index + 1 , number + numArr[index]);
//                            System.out.print("+");
                        break;
                    case 1:
                        recursive(index + 1 , number - numArr[index]);
//                            System.out.print("-");
                        break;
                    case 2:
                        recursive(index + 1 , number * numArr[index]);
//                            System.out.print("*");
                        break;
                    case 3:
                        recursive(index + 1 , number / numArr[index]);
//                            System.out.print("/");
                        break;
                }
                symbolArr[i] += 1;
            }
        }
    }
}