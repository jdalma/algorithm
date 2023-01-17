package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class DSLR {
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        for(int row = 0 ; row < size ; row++){
            int start = sc.nextInt();
            int target = sc.nextInt();
//            System.out.println(start + " -> " + target);
            Queue<Number> numberQ = new LinkedList<>();
            visited = new boolean[10000];
            visited[start] = true;
            numberQ.offer(new Number(start , ""));

            while(!numberQ.isEmpty()){
                Number num = numberQ.poll();
                if(num.value == target){
                    System.out.println(num.control);
                    break;
                }
                if(!visited[num.D()]){
                    visited[num.D()] = true;
                    numberQ.offer(new Number(num.D() , num.control + "D"));
                }
                if(!visited[num.S()]){
                    visited[num.S()] = true;
                    numberQ.offer(new Number(num.S() , num.control + "S"));
                }
                if(!visited[num.L()]){
                    visited[num.L()] = true;
                    numberQ.offer(new Number(num.L() , num.control + "L"));
                }
                if(!visited[num.R()]){
                    visited[num.R()] = true;
                    numberQ.offer(new Number(num.R() , num.control + "R"));
                }
            }
        }
    }

    private static class Number{
        int value;
        String control;
        public Number(int value, String control) {
            this.value = value;
            this.control = control;
        }
        // D : 값을 2배로 바꾼다.
        // 결과 값이 10000보다 클 경우 10000으로 나눈 나머지를 취한다.
        // 2값 mod 10000
        public int D(){
            return (value * 2) % 10000;
        }
        // S : 값에서 1을 뺀다.
        // 계산 전 값이 0이라면 9999가 저장된다.
        public int S(){
            return value == 0 ? 9999 : value - 1;
        }
        // L : 값의 각 자릿수를 왼편으로 회전시킨다.
        public int L(){
            return ((value % 1000) * 10) + value / 1000;
        }
        // R : 값의 각 자릿수를 오른편으로 회전 시킨다.
        public int R(){
            return ((value % 10) * 1000) + value / 10;
        }
    }
}