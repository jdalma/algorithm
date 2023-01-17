package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 연결_요소의_개수 {
    static boolean[] checked;
    static List<List<Integer>> list = new ArrayList<>();
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        checked = new boolean[vertex + 1];
        for(int i = 0 ; i <= vertex ; i++) list.add(new ArrayList<Integer>());
        for(int i = 0 ; i < edge ; i++){
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            list.get(vertex1).add(vertex2);
            list.get(vertex2).add(vertex1);
        }

        for(int i = 1 ; i <= vertex ; i++){
            if(!checked[i]){
                result++;
                bfs(i);
            }
        }

        System.out.println(result);
    }

    public static void bfs(int vertex){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while(!queue.isEmpty()){
            int nowVertex = queue.poll();
            checked[nowVertex] = true;
            for(int linkVertex : list.get(nowVertex)){
                if(!checked[linkVertex]){
                    checked[linkVertex] = true;
                    queue.offer(linkVertex);
                }
            }
        }
    }
}