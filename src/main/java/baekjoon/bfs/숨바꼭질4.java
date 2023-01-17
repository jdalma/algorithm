package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 숨바꼭질4 {
    static int[] move = {1 , -1 , 2};
    static int[] moveDistance;
    static int[] distance;
    static int start , destination;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        moveDistance = new int[100002];
        distance = new int[100002];
        Arrays.fill(distance , Integer.MAX_VALUE);
        bfs();
    }

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {start , 0});
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            if(now[0] == destination){
                System.out.println(now[1]);
                int footPrintIndex = destination;
                Deque<Integer> footPrint = new LinkedList<>();
                footPrint.addFirst(footPrintIndex);
                while(footPrintIndex != start){
                    footPrint.addFirst(moveDistance[footPrintIndex]);
//                    System.out.println(moveDistance[footPrintIndex] + " : " + footPrintIndex);
                    footPrintIndex = moveDistance[footPrintIndex];
                }
                while(!footPrint.isEmpty()){
                    System.out.print(footPrint.poll() + " ");
                }
                System.exit(0);
            }
            for(int i = 2 ; i >= 0 ; i--){
                int movePos = i == 2 ? now[0] * move[i] : now[0] + move[i];
                int moveCnt = now[1] + 1;
                if(movePos >= 0 && movePos < 100002 && distance[movePos] >= moveCnt){
                    distance[movePos] = moveCnt;
                    moveDistance[movePos] = now[0];
                    queue.offer(new int[] {movePos , moveCnt});
                }
            }
        }
    }
}