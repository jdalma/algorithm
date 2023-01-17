package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class A에서_B로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double start = Double.parseDouble(st.nextToken());
        double target = Double.parseDouble(st.nextToken());
        bfs(start , target);
        System.out.println(-1);
    }

    public static void bfs(double start , double target){
        Queue<double[]> queue = new LinkedList<>();
        queue.offer(new double[] {start , 1});
        while(!queue.isEmpty()){
            double[] now = queue.poll();
            double nowValue = now[0];

            double count = now[1];
            if(nowValue == target){
                System.out.println((int)count);
                System.exit(0);
            }
            else{
                double value1 = nowValue * 2;
                double value2 = nowValue * 10 + 1;
                if(value1 <= target) queue.offer(new double[]{value1 , count + 1});
                if(value2 <= target) queue.offer(new double[]{value2 , count + 1});
            }
        }
    }
}