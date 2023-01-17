package baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 - 우선순위 큐에서 나오는 기준을 `cost`가 높은 순으로 나오게 한다.
 - **이미 낮은 곳이 먼저 들려버린다면 갈 수 있는 경우의 수를 모두 확인할 수 없다**
 - **`count[][]`를 둬서 처음 가는 지역에만 `Queue`에 넣고 기존에 들렸던 곳인데 `cost`가 낮다면 현재 위치에 담겨있는 경우의 수를 더 해준다.**
 */
class 내리막길 {

    static int[][] moves4Dir = { {-1 , 0} , {0 , 1} , {1 , 0} , {0 , -1} };
    static int row , col;
    static int[][] map;
    static int[][] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        count = new int[row][col];

        for(int i = 0 ; i < row ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < col ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        bw.append(String.valueOf(count[row - 1][col - 1]));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs() {
        PriorityQueue<Move> q = new PriorityQueue<>();
        q.offer(new Move(0 , 0 , map[0][0]));
        count[0][0] = 1;

        while(!q.isEmpty()) {
            Move now = q.poll();
            for(int[] move : moves4Dir) {
                int moveX = now.x + move[0];
                int moveY = now.y + move[1];
                if(!isOutOfRange(moveX , moveY) && map[moveX][moveY] < map[now.x][now.y]) {
                    if(count[moveX][moveY] == 0) {
                        q.offer(new Move(moveX , moveY , map[moveX][moveY]));
                    }
                    count[moveX][moveY] += count[now.x][now.y];
                }
            }
        }
    }

    public static boolean isOutOfRange(int x , int y) {
        if(x < 0 || x >= row || y < 0 || y >= col) {
            return true;
        }
        return false;
    }

    static class Move implements Comparable<Move>{
        int x , y , cost;

        public Move(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Move o) {
            return o.cost - this.cost;
        }
    }

}
