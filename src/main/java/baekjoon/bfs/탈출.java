package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 탈출 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static char[][] map;
    static boolean[][] visited;
    static int row , col;
    static int[] startPos;
    static List<int[]> waterPosList = new ArrayList<int[]>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        visited = new boolean[row][col];
        for(int i = 0 ; i < row ; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < col ; j++) {
                char ch = line[j];
                if(ch == 'S') startPos = new int[] {i , j , 0};
                else if(ch == '*') waterPosList.add(new int[] {i , j , 0});
                map[i][j] = ch;
            }
        }
        bfs();
    }

    private static void bfs() {
        int timeCount = 0;
        Queue<int[]> waterQueue = new LinkedList<int[]>();
        for(int[] waterPos : waterPosList) {
            waterQueue.add(waterPos);
            visited[waterPos[0]][waterPos[1]] = true;
        }

        Queue<int[]> movingQueue = new LinkedList<int[]>();
        movingQueue.add(startPos);
        visited[startPos[0]][startPos[1]] = true;

        while(true) {
            while(!waterQueue.isEmpty()) {
                int[] waterNow = waterQueue.peek();
                if(waterNow[2] == timeCount) {
                    // 물 이동
                    moving(waterQueue , waterQueue.poll() , 'w');
                }
                else break;
            }

            while(!movingQueue.isEmpty()) {
                int[] movingNow = movingQueue.peek();
                if(movingNow[2] == timeCount) {
                    // 고슴도치 이동
                    moving(movingQueue , movingQueue.poll() , 'm');
                }
                else break;
            }
            timeCount++;
            if(waterQueue.isEmpty() && movingQueue.isEmpty()) {
                System.out.println("KAKTUS");
                System.exit(0);
            }
        }
    }

    private static void moving(Queue<int[]> queue , int[] pos , char moveFlag) {
        for(int i = 0 ; i < 4 ; i++) {
            int moveXpos = pos[0] + moveX[i];
            int moveYpos = pos[1] + moveY[i];
            int time = pos[2] + 1;
            if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < row && moveYpos < col && !visited[moveXpos][moveYpos]) {
                if(moveFlag == 'm' && map[moveXpos][moveYpos] == 'D') {
                    System.out.println(time);
                    System.exit(0);
                }
                if(map[moveXpos][moveYpos] == '.') {
                    queue.offer(new int[] {moveXpos , moveYpos , time});
                    visited[moveXpos][moveYpos] = true;
                }
            }
        }
    }
}