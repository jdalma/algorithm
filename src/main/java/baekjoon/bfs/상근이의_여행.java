package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 상근이의_여행 {
    static List<ArrayList<Integer>> list;
    static boolean[] checked;
    static StringBuilder resultString = new StringBuilder();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < testCase ; i++) {
            result = -1;
            list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int countryCount = Integer.parseInt(st.nextToken());
            int airplaneCount = Integer.parseInt(st.nextToken());
            checked = new boolean[countryCount + 1];
            for(int j = 0 ; j <= countryCount ; j++) list.add(new ArrayList<Integer>());
            for(int j = 0 ; j < airplaneCount ; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int destination = Integer.parseInt(st.nextToken());
                list.get(start).add(destination);
                list.get(destination).add(start);
            }
            solve();
            resultString.append(result).append("\n");
        }
        System.out.println(resultString.toString());
    }

    public static void solve() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        checked[1] = true;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            result++;
            for(int value : list.get(now)) {
                if(!checked[value]) {
                    checked[value] = true;
                    queue.offer(value);
                }
            }
        }
    }
}