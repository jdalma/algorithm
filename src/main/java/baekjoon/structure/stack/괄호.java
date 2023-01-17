package baekjoon.structure.stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 괄호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < size ; i++){
            char[] chArr = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            for(char ch : chArr){
                if(ch == ')' && !stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }
                else stack.push(ch);
            }
            if(stack.isEmpty()) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
