package baekjoon.divideAndConquer;

import java.io.*;
import java.util.*;

class 투에모스_문자열 {
    static int count = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long findIndex = Long.parseUnsignedLong(br.readLine());

        bw.append(reverse(find(findIndex - 1)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(long index) {
        count++;
        if (index == 0) return 0;
        else if (index == 1) return 1;
        else if (index % 2 == 0) return 1 - find(index / 2);
        else return find(index / 2);
    }

    public static String reverse(int value) {
        if(count % 2 == 0) return String.valueOf(value);
        else{
            if (value == 0) return "1";
            return "0";
        }
    }
}
