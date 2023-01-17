package baekjoon.bfs;

import java.io.*;
import java.util.*;

/**
 * - "굳이 파도 한 번마다 모래성 주위 8방향을 탐색하며 갯수 셀 필요가 있을까?"
 * - **모래성은 어차피 줄어들고, 빈 모래는 늘어나니까.**
 * - "BFS를 사용해서 빈 모래 근방 모래성들 크기를 1씩 떨어뜨리면 되지 않을까?"
 * - "어차피 한 번만 빈 모래 사용하면 이후에는 굳이 사용할 필요가 없지. 빈 모래는 다시 찾지 않아도 되니."
 * - 위의 힌트를 통해 풀어보자!!!
 * - [출처](https://maivve.tistory.com/128)
 */

class 모래성 {
    static char[][] map;
    static char[][] copy;
    static int[] checkX = {-1 , -1 , -1 , 0 , 1 , 1 , 1 , 0};
    static int[] checkY = {-1 , 0 , 1 , 1 , 1 , 0 , -1 , -1};
    static int rowSize , colSize , count , brokenCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        Queue<Position> emptySands = new LinkedList<Position>();

        for(int i = 0 ; i < rowSize ; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < colSize ; j++) {
                map[i][j] = line[j];
                if(map[i][j] == '.') emptySands.offer(new Position(i , j));
            }
        }
        count = 0;
        while(!emptySands.isEmpty()) {
            brokenCount = 0;
            emptySands = BFS(emptySands);
            if(brokenCount > 0) count++;
        }

        bw.append(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    public static Queue<Position> BFS(Queue<Position> emptySands) {
        Queue<Position> newEmptySands = new LinkedList<Position>();

        while(!emptySands.isEmpty()) {
            Position emptySand = emptySands.poll();
            for(int i = 0 ; i < checkX.length ; i++) {
                int moveX = emptySand.x + checkX[i];
                int moveY = emptySand.y + checkY[i];
                if(moveX >= 0 && moveX < rowSize  && moveY >= 0 && moveY < colSize) {
                    if(map[moveX][moveY] != '.') {
                        if(--map[moveX][moveY] == '0') {
                            newEmptySands.offer(new Position(moveX , moveY));
                            map[moveX][moveY] = '.';
                            brokenCount++;
                        }
                    }
                }
            }
        }
        return newEmptySands;
    }

    public static void print() {
        for(int i = 0 ; i < rowSize ; i++) {
            for(int j = 0 ; j < colSize ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Position{
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
