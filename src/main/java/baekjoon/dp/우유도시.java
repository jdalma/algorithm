package baekjoon.dp;

import java.io.*;
import java.util.*;


class 우유도시 {
    static int[][] map;
    static int[][][] dp;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());

        map = new int[size][size];
        dp = new int[size][size][3];

        for(int i = 0 ; i < size ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < size ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(map[0][0] == 0) {
            dp[0][0][0] = 1;
        }

        // 0행 초기화
        for(int i = 1 ; i < size ; i++){
            int milk = map[0][i];
            // dp[0][i][0]에는 현재가 딸기우유가 맞으면 전 열의 바나나우유 + 1 , 딸기우유가 아니면 전 열의 딸기우유 그대로
            dp[0][i][0] = milk == 0 ? dp[0][i - 1][2] + 1 : dp[0][i - 1][0];

            // dp[0][i][1]에는 현재가 초코우유가 맞고 , 전 열의 딸기우유가 바나나우유보다 크다면 초코우유를 마시는 순서가 맞으므로 전 열의 딸기우유 + 1
            dp[0][i][1] = milk == 1 && dp[0][i - 1][0] > dp[0][i - 1][2] ? dp[0][i][0] + 1 : dp[0][i - 1][1];

            // dp[0][i][2]에는 현재가 바나나우유가 맞고 , 전 열의 초코우유가 딸기우유보다 크다면 바나나우유를 마시는 순서가 맞으므로 전 열의 초코우유 + 1
            dp[0][i][2] = milk == 2 && dp[0][i - 1][1] > dp[0][i - 1][0] ? dp[0][i - 1][1] + 1 : dp[0][i - 1][2];
        }

        // 0열 초기화
        for(int i = 1 ; i < size ; i++){
            int milk = map[i][0];
            dp[i][0][0] = milk == 0 ? dp[i - 1][0][2] + 1 : dp[i - 1][0][0];
            dp[i][0][1] = milk == 1 && dp[i - 1][0][0] > dp[i - 1][0][2] ? dp[i - 1][0][0] + 1 : dp[i - 1][0][1];
            dp[i][0][2] = milk == 2 && dp[i - 1][0][1] > dp[i - 1][0][0] ? dp[i - 1][0][1] + 1 : dp[i - 1][0][2];
        }


        for(int i = 1 ; i < size ; i++){
            for(int j = 1 ; j < size ; j++){
                int milk = map[i][j];
                dp[i][j][0] = milk == 0 ? Math.max(dp[i][j - 1][2] , dp[i - 1][j][2]) + 1 : Math.max(dp[i][j - 1][0] , dp[i - 1][j][0]);
                dp[i][j][1] = milk == 1 && dp[i][j][0] > dp[i][j][2] ? Math.max(dp[i][j - 1][0] , dp[i - 1][j][0]) + 1 : Math.max(dp[i][j - 1][1] , dp[i - 1][j][1]);
                dp[i][j][2] = milk == 2 && dp[i][j][1] > dp[i][j][0] ? Math.max(dp[i][j - 1][1] , dp[i - 1][j][1]) + 1 : Math.max(dp[i][j - 1][2] , dp[i - 1][j][2]);
            }
        }

        bw.append(String.valueOf(Math.max(dp[size - 1][size - 1][0] , Math.max(dp[size - 1][size - 1][1] , dp[size - 1][size - 1][2]))));
        bw.flush();
        bw.close();
        br.close();
    }
}