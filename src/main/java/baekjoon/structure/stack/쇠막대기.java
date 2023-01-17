package baekjoon.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 쇠막대기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chArr = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        for(int i = 0 ; i < chArr.length ; i++){
            if(chArr[i] == ')'){
                stack.pop();
//                System.out.print(stack.size() + " ");
                if(chArr[i - 1] == '(') result += stack.size();
                else result++;
            }
            else if(chArr[i] == '('){
                stack.push(chArr[i]);
            }
        }
        System.out.println(result);
    }
}