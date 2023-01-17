package baekjoon.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Stack<long[]> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        String[] arr = br.readLine().split(" ");
        stack.push(new long[]{Long.parseLong(arr[0]) , 1});
        boolean flag;
        result.append("0 ");
        for(int i = 1 ; i < size ; i++){
            flag = false;
            long value = Long.parseLong(arr[i]);
            while(!stack.isEmpty()){
                if(stack.peek()[0] >= value){
                    result.append(stack.peek()[1]).append(" ");
                    stack.push(new long[]{value , i + 1});
                    flag = true;
                    break;
                }
                else{
                    stack.pop();
                }
            }
            if(!flag) {
                stack.push(new long[]{value , i + 1});
                result.append("0 ");
            }
        }
        System.out.println(result.toString());
    }

}