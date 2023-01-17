package baekjoon.dfs;

import java.util.*;
import java.io.*;

public class 바이러스 {
    static List<ArrayList<Integer>> link = new ArrayList<>();
    static boolean[] checked;
    static int nodeSize , rowSize;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        nodeSize = Integer.parseInt(br.readLine());
        rowSize = Integer.parseInt(br.readLine());
        checked = new boolean[nodeSize + 1];
        for(int i = 0 ; i < nodeSize + 1 ; i++) link.add(new ArrayList<Integer>());
        for(int i = 0 ; i < rowSize ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            link.get(start).add(end);
            link.get(end).add(start);
        }
//    	bfs(); // 메모리 초과
        checked[1] = true;
        dfs(1);
        System.out.println(result);
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(1);
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(int newNode : link.get(node)) {
                queue.offer(newNode);
                result++;
            }
        }
    }

    public static void dfs(int node) {
        for(int nearNode : link.get(node)) {
            if(!checked[nearNode]) {
                checked[nearNode] = true;
                result++;
                dfs(nearNode);
            }
        }
    }
}
