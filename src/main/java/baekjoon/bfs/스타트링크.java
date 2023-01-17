package baekjoon.bfs;

import java.io.IOException;
import java.util.*;

// 문제를 꼼꼼히 읽자
// `(만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)`

class 스타트링크 {
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int F = sc.nextInt();   // 총 F 층
        int S = sc.nextInt();   // 현재 층
        int G = sc.nextInt();   // 목표 층
        int U = sc.nextInt();   // 위로 올라갈 수 있는 층의 수
        int D = sc.nextInt();   // 밑으로 내려갈 수 있는 있는 층의 수

        Queue<Move> controlQ = new LinkedList<>();
        controlQ.offer(new Move(S , 0));

        visited = new boolean[F + 1];
        visited[S] = true;
        boolean flag = true;
        while(!controlQ.isEmpty()){
//            System.out.println(controlQ);
            Move now = controlQ.poll();
            if(now.floor == G){
                System.out.println(now.moveCount);
                flag = false;
                break;
            }
            int up = now.floor + U;
            int down = now.floor - D;
            if(up <= F && !visited[up]){
                visited[up] = true;
                controlQ.offer(new Move(up , now.moveCount + 1));
            }
            if(down > 0 && !visited[down]){
                visited[down] = true;
                controlQ.offer(new Move(down , now.moveCount + 1));
            }
        }
        if(flag) System.out.println("use the stairs");
    }

    private static class Move{
        int floor;
        int moveCount;
        public Move(int floor, int moveCount) {
            this.floor = floor;
            this.moveCount = moveCount;
        }
        @Override
        public String toString() {
            return "Move{" +
                    "floor=" + floor +
                    ", moveCount=" + moveCount +
                    '}';
        }
    }
}
