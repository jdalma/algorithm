package baekjoon.bfs;

import java.io.*;
import java.util.*;

class 빙산 {
    static int[][] moves4dir = { {-1 , 0} , {0 , 1} , {1 , 0} , {0 , -1} };
    static int[][] map;
    static Queue<Position> waterPos = new ArrayDeque<Position>();
    static int row , col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for(int i = 0 ; i < row ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < col ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    waterPos.offer(new Position(i , j));
                }
            }
        }

        int meltCount = 0;
        boolean findChunks = false;
        while(!waterPos.isEmpty()) {
            melt();
//			print();
            meltCount++;
            if(findChunks = findChunk()) {
                break;
            }
        }

        bw.append(findChunks ? String.valueOf(meltCount) : "0");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean findChunk() {
        int chunkCount = 0;
        boolean[][] checked = new boolean[row][col];

        for(int i = 0 ; i < row ; i++) {
            for(int j = 0 ; j < col ; j++) {
                if(!checked[i][j] && map[i][j] > 0) {
                    checkChunk(i , j , checked);
                    chunkCount++;
                }
            }
        }

        return chunkCount > 1;
    }

    public static void checkChunk(int x , int y , boolean[][] checked) {
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(x , y));
        checked[x][y] = true;

        while(!queue.isEmpty()) {
            Position water = queue.poll();
            for(int[] move : moves4dir) {
                int moveX = water.x + move[0];
                int moveY = water.y + move[1];
                if(moveX >= 0 && moveX < row && moveY >= 0 && moveY < col) {
                    if(!checked[moveX][moveY] && map[moveX][moveY] > 0) {
                        checked[moveX][moveY] = true;
                        queue.offer(new Position(moveX , moveY));
                    }
                }
            }
        }
    }

    public static void melt() {
        int waterPosSize = waterPos.size();
        for(int i = 0 ; i < waterPosSize ; i++) {
            boolean icebergNear = false;
            Position water = waterPos.poll();
            for(int[] move : moves4dir) {
                int moveX = water.x + move[0];
                int moveY = water.y + move[1];
                if(moveX >= 0 && moveX < row && moveY >= 0 && moveY < col) {
                    if(map[moveX][moveY] > 0) {
                        icebergNear = true;
                        --map[moveX][moveY];
                        if(map[moveX][moveY] == 0) {
                            waterPos.add(new Position(moveX , moveY));
                        }
                    }
                }
            }
            if(icebergNear) waterPos.offer(water);
        }
    }

    public static void print() {
        for(int i = 0 ; i < row ; i++) {
            for(int j = 0 ; j < col ; j++) {
                System.out.print(map[i][j] + " ");
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
