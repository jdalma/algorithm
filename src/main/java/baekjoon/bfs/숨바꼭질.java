package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 숨바꼭질 {
    static int[] move = {1 , -1 , 2};
    static int minTime = 0;
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        bfsSolution(start , destination);
        System.out.println(minTime);
    }

    public static void bfsSolution(int start , int destination){
        Queue<Moving> queue = new LinkedList<>();
        queue.offer(new Moving(start , 0));
        visited[start] = true;
        while(!queue.isEmpty()){
            Moving now = queue.poll();
            if(now.position == destination) {
                minTime = now.passedTime;
                break;
            }
            else{
                for(int i = 0 ; i < move.length ; i++){
                    int moving = i == 2 ? now.position * i : now.position + move[i];
                    if(moving >= 0 && moving < 100001 && !visited[moving]){
                        visited[moving] = true;
                        queue.offer(new Moving(moving , now.passedTime + 1));
                    }
                }
            }
        }
    }

    private static class Moving{
        int position;
        int passedTime;
        public Moving(int position, int passedTime) {
            this.position = position;
            this.passedTime = passedTime;
        }
    }
}