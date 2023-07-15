package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * ## **유클리드 호제법**
 * - **최대공약수를 구하는 알고리즘의 하나이다.**
 * - 호제법이란 말은 두 수가 서로 상대방 수를 나누어서 결국 원하는 수를 얻는 알고리즘을 나타낸다.
 */

class GCD_합_유클리드호제법 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < size ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numberCount = Integer.parseInt(st.nextToken());
            List<Integer> numbers = new ArrayList<Integer>();
            for(int j = 0 ; j < numberCount ; j++){
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            sb.append(solve(numbers)).append("\n");
        }
        System.out.println(sb);
    }

    public static long solve(List<Integer> numbers){
        long sum = 0;
        for(int i = 0 ; i < numbers.size() - 1; i++){
            for(int j = i + 1 ; j < numbers.size() ; j++){
                sum += GCD(numbers.get(i) , numbers.get(j));
            }
        }
        return sum;
    }

    public static int GCD(int a , int b){
        if(b == 0) return a;
        return GCD(b , a % b);
    }

}
