package baekjoon.dfs.backtracking;

import java.io.*;

/**
 * - [분석 필요](https://chanhuiseok.github.io/posts/baek-1/)
 */
class N_Queen {
    static int[] position;
    static int size , count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());
        position = new int[size];

        place(0);

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void place(int col) {
        if(col == size) {
            count++;
            return;
        }
        for(int i = 0 ; i < size ; i++) {
            position[col] = i;
            if(isPossible(col)) {
                place(col + 1);
            }
        }
    }

    public static boolean isPossible(int col) {
        for(int i = 0 ; i < col ; i++) {
            if(position[col] == position[i] || col - i == Math.abs(position[col] - position[i]))
                return false;
        }
        return true;
    }
}