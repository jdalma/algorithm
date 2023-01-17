package baekjoon.bfs;

import java.io.*;
import java.util.*;

class 로봇 {
    static int[] moveX = {0 , 0 , 1 , -1};
    static int[] moveY = {1 , -1 , 0 , 0};
    static int[][] map;
    static int[][] checked;
    static Position start , destination;
    static int rowSize , colSize , result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        checked = new int[rowSize][colSize];

        for(int i = 0 ; i < rowSize ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < colSize ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                checked[i][j] = Integer.MAX_VALUE;
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new Position(Integer.parseInt(st.nextToken()) - 1 , Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) , 0);

        st = new StringTokenizer(br.readLine());
        destination = new Position(Integer.parseInt(st.nextToken()) - 1 , Integer.parseInt(st.nextToken()) - 1 , Integer.parseInt(st.nextToken()) , 0);

        BFS();

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS(){
        Queue<Position> queue = new LinkedList<>();
        checked[start.x][start.y] = 0;
        queue.offer(start);

        // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
        while(!queue.isEmpty()){
            Position now = queue.poll();
//            System.out.println(queue);
            int nowDirection = now.direction;
            if(isSame(now , destination)){
                result = Math.min(now.commandCount + getDirectionDiffCount(now.direction , destination.direction) , result);
                checked[destination.x][destination.y] = result;
            }
            for(int i = 1 ; i <= 4 ; i++){
                int directionDiff = getDirectionDiffCount(nowDirection , i);
                for(int go = 0 ; go < 3 ; go++){
                    int moveXdist = getMoveDist(moveX[i - 1] , go);
                    int moveYdist = getMoveDist(moveY[i - 1] , go);
                    int moveXpos = now.x + moveXdist;
                    int moveYpos = now.y + moveYdist;
                    if(moveXpos >= 0 && moveXpos < rowSize && moveYpos >= 0 && moveYpos < colSize){
                        // 궤도가 없는 곳을 만나면 break하고 해당 방향은 진행하지 않는다.
                        if(map[moveXpos][moveYpos] == 1) break;
                        int moveCommand = now.commandCount + directionDiff + 1;
                        if(checked[moveXpos][moveYpos] > moveCommand){
                            checked[moveXpos][moveYpos] = moveCommand;
                            queue.offer(new Position(moveXpos , moveYpos , i , moveCommand));
                        }
                    }
                }
            }
        }
    }

    public static boolean isSame(Position o1 , Position o2){
        return o1.x == o2.x && o1.y == o2.y;
    }
    //        4
//    2       1
//        3
    public static int getDirectionDiffCount(int d1 , int d2){
        if(d1 == d2) return 0;
        else if(d1 == 1 || d1 == 2){
            if(d2 == 3 || d2 == 4) return 1;
            return 2;
        }
        else{
            if(d2 == 1 || d2 == 2) return 1;
            return 2;
        }

    }
    public static int getMoveDist(int move , int dist){
        if(move == 0) return 0;
        if(move > 0) return move + dist;
        return move + (dist * -1);
    }

    static class Position{
        int x;
        int y;
        int direction;
        int commandCount;

        public Position(int x, int y, int direction , int commandCount) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.commandCount = commandCount;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    ", commandCount=" + commandCount +
                    '}';
        }
    }
}
