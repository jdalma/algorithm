package baekjoon.math;

import java.io.*;

class 소수{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        int min = Integer.MAX_VALUE;

        mainLoop:
        for(int i = M ; i <= N ; i++){
            if(i == 1) continue;
            for(int j = 2 ; j < i ; j++){
                if(i % j == 0) {
                    continue mainLoop;
                }
            }
            sum += i;
            min = Math.min(min , i);
        }

        String result = sum == 0 ? "-1" : sum + "\n" + min;

        bw.append(result);
        bw.flush();
        bw.close();
        br.close();
    }
}
