package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - 경과 시간을 배열에 누적 시킨다.
 * - Queue에 담을 조건을 못 찾았었다.
 *   - [참고](https://bcp0109.tistory.com/154)
 *   - **(해당 인덱스를 처음 방문)하거나 (방문할 인덱스의 시간과 이전에 방문한 인덱스의 시간 + 1과 같다면) Queue에 담는다**
 */

class 숨바꼭질2 {
    static int[] move = {1 , -1 , 2};
    static int minTime = Integer.MAX_VALUE;
    static int minCount = 0;
    static int[] distance = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        if(start == destination) {
            System.out.println(0);
            System.out.println(1);
        }
        else {
            bfsSolution(start , destination);
            System.out.println(minTime);
            System.out.println(minCount);
        }
    }

    public static void bfsSolution(int start , int destination){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distance[start] = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            if(minTime < distance[now]) continue;

            for(int i = 0 ; i < move.length ; i++){
                int moving = i == 2 ? now * move[i] : now + move[i];
                if (moving >= 0 && moving < 100001) {
                    if(moving == destination){
//                        System.out.println(moving + " : " + (distance[now] + 1));
                        if(minTime > distance[now] + 1){
                            minTime = distance[now] + 1;
                            minCount = 1;
                        }
                        else if(minTime == distance[now] + 1) minCount++;
                    }
                    else if(distance[moving] == 0 || distance[moving] == distance[now] + 1){
                        distance[moving] = distance[now] + 1;
                        queue.offer(moving);
                    }
                }
            }
        }
    }
}