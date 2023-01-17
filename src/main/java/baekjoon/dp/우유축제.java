package baekjoon.dp;

import java.io.*;
import java.util.*;


class 우유축제 {
    static int[] map;
    static int[][] dp;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());

        map = new int[size];
        dp = new int[3][size];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < size ; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        if(map[0] == 0) {
            dp[0][0] = 1;
        }

        for(int i = 1 ; i < size ; i++){
            int milk = map[i];
            dp[0][i] = milk == 0 ? dp[2][i - 1] + 1 : dp[0][i - 1];
            dp[1][i] = milk == 1 && dp[0][i - 1] > 0 ? dp[0][i - 1] + 1 : dp[1][i - 1];
            dp[2][i] = milk == 2 && dp[1][i - 1] > 0 ? dp[1][i - 1] + 1 : dp[2][i - 1];
        }

        bw.append(String.valueOf(Math.max(dp[0][size - 1] , Math.max(dp[1][size - 1] , dp[2][size - 1]))));
        bw.flush();
        bw.close();
        br.close();
    }
}
