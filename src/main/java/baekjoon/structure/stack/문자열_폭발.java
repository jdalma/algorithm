package baekjoon.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 참고 https://leveloper.tistory.com/112
 */

class 문자열_폭발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String replaceStr = br.readLine();

        String result = solution(str , replaceStr);
        System.out.println("".equals(result) ? "FRULA" : result);
    }

    private static String solution(String str , String replaceStr){
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < str.length() ; i++){
            stack.push(str.charAt(i));
            if(stack.size() >= replaceStr.length()){
                boolean flag = true;
                for(int j = 0 ; j < replaceStr.length() ; j++){
                    if(stack.get(stack.size() - replaceStr.length() + j) != replaceStr.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    for(int j = 0 ; j < replaceStr.length() ; j++){
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < stack.size() ; i++) sb.append(stack.get(i));
        return sb.toString();
    }
}