package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 스타트와_링크 {
    static int[][] arr;
    static int[] check;
    static int size;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        arr = new int[size + 1][size + 1];
        check = new int[size + 1];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }
        combiRecursive(1, 0);
        System.out.println(result);
    }

    public static void combiRecursive(int index, int count) {
        if (count == size / 2) {
            stat();
        } else {
            for (int i = index; i <= size; i++) {
                check[i] = 1;
                combiRecursive(i + 1, count + 1);
                check[i] = 0;
            }
        }
    }

    public static void stat() {
        int[] start = new int[size / 2];
        int[] link = new int[size / 2];
        int startIndex = 0, linkIndex = 0;
        int startSum = 0, linkSum = 0;
        for (int i = 1; i <= size; i++) {
            if (check[i] == 1) start[startIndex++] = i;
            else link[linkIndex++] = i;
        }

        for (int i = 0; i < size / 2; i++) {
            for (int j = i; j < size / 2; j++) {
                startSum += arr[start[i]][start[j]] + arr[start[j]][start[i]];
                linkSum += arr[link[i]][link[j]] + arr[link[j]][link[i]];
            }
        }
        result = Math.min(result, Math.abs(startSum - linkSum));
    }
}