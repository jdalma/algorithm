package baekjoon.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 크게_만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int indexSize = Integer.parseInt(st.nextToken());
        int removeNum = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<Integer>();
        String[] strArr = br.readLine().split("");
        int count = 0;
        for(String str : strArr){
            int value = Integer.parseInt(str);
            while(count < removeNum && !stack.isEmpty() && stack.peek() < value) {
                stack.pop();
                count++;
            }
            stack.push(value);
        }
        for(int i = 0; i< indexSize - removeNum; i++) {
            System.out.print(stack.get(i));
        }
    }
}