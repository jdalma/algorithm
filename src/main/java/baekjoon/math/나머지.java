package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class 나머지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        StringBuilder result = new StringBuilder();
        result.append( (A + B) % C ).append("\n");
        result.append( ((A % C) + (B % C)) % C).append("\n");
        result.append( (A * B) % C ).append("\n");
        result.append( ((A % C) * (B % C)) % C).append("\n");
        System.out.println(result.toString());
    }
}