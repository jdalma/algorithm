package baekjoon.dp;

import java.util.*;
import java.io.*;

public class 퇴사 {
    static int[] time , cost , dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        time = new int[count];
        cost = new int[count];
        dp = new int[count + 1];

        for(int i = 0 ; i < count ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // 점화식
        // 현재 날짜에서 소요 시간과 비용을 더해 dp에 저장한다.
        // 이후, 중복될 때 최대값을 넣는다.

        for(int i = 0 ; i < count ; i++){
            if(i + time[i] <= count){
                // 날짜가 범위를 넘어가지 않는 경우
                dp[i + time[i]] = Math.max(dp[i] + cost[i] , dp[i + time[i]]);
            }
            // 해당 경우의 수가 0일 수 있기 때문에 이전의 최대값을 넣어줌
            // 해당 날짜에 일할 수 없다면 , 이전까지 일한 최대 수당을 넣어주어야 한다.
            dp[i + 1] = Math.max(dp[i] , dp[i + 1]);

//            for(int value : dp) System.out.print(value + " ");
//            System.out.println();
        }
        System.out.println(dp[count]);
    }
}