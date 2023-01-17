package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class DFS와BFS {
    static boolean[] visited;
    static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        int startVertex = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i <= vertexCount ; i++) list.add(new ArrayList<Integer>());
        for(int i = 0 ; i < edgeCount ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }
        for(int i = 0 ; i <= vertexCount ; i++) Collections.sort(list.get(i));

        visited = new boolean[vertexCount + 1];
        visited[startVertex] = true;
        dfs(startVertex);
        System.out.println();
        visited = new boolean[vertexCount + 1];
        visited[startVertex] = true;
        bfs(startVertex);

    }
    public static void dfs(int startVertex) {
        System.out.print(startVertex + " ");
        for(int value : list.get(startVertex)){
            if(!visited[value]){
                visited[value] = true;
                dfs(value);
            }
        }
    }

    public static void bfs(int startVertex){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(startVertex);
        while(!queue.isEmpty()){
            int now = queue.poll();
            System.out.print(now + " ");
            for(int value : list.get(now)){
                if(!visited[value]) {
                    visited[value] = true;
                    queue.offer(value);
                }
            }

        }
    }
}