package baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 날짜_계산 {
    static int[] stan = {15 , 28 , 19};
    static int[] year = new int[3];
    static int[] now = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        year[0] = Integer.parseInt(st.nextToken());
        year[1] = Integer.parseInt(st.nextToken());
        year[2] = Integer.parseInt(st.nextToken());

        int count = 0;

        while(true){
            count++;
            for(int i = 0 ; i < 3 ; i++){
                now[i] += 1;
                if(now[i] > stan[i]) now[i] = 1;
            }
            if(isSame()){
                break;
            }
        }
        System.out.println(count);
    }
    public static boolean isSame(){
        boolean result = true;
        for(int i = 0 ; i < 3 ; i++){
            if (now[i] != year[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
