package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 토마토 {
    static int[][][] map;
    static boolean[][][] checked;
    static int[] moveZ = {1, 0, 0, 0, 0, -1};
    static int[] moveX = {0, -1, 0, 1, 0, 0};
    static int[] moveY = {0, 0, 1, 0, -1, 0};
    static int result = -1;
    static int x, y, z;
    static boolean flag = false;
    static Queue<Position> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());
        map = new int[x][y][z];
        checked = new boolean[x][y][z];
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < x; j++) {
                String[] line = br.readLine().split(" ");
                for (int k = 0; k < y; k++) {
                    int value = Integer.parseInt(line[k]);
                    if (value == 0) flag = true;
                    else if (value == 1) {
                        queue.offer(new Position(j, k, i, 0));
                        checked[j][k][i] = true;
                    }
                    map[j][k][i] = value;
                }
            }
        }
        if (!flag) {
            System.out.println(0);
        } else {
            bfs();
            for (int i = 0; i < z; i++) {
                for (int j = 0; j < x; j++) {
                    for (int k = 0; k < y; k++) {
                        if (map[j][k][i] == 0) {
                            System.out.println(-1);
                            System.exit(0);
                        }
                    }
                }
            }
            System.out.println(result);
        }
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            for (int i = 0; i < 6; i++) {
                int moveXpos = now.x + moveX[i];
                int moveYpos = now.y + moveY[i];
                int moveZpos = now.z + moveZ[i];
                if (moveXpos >= 0 && moveYpos >= 0 && moveZpos >= 0 && moveXpos < x && moveYpos < y && moveZpos < z) {
                    if (map[moveXpos][moveYpos][moveZpos] == 0 && !checked[moveXpos][moveYpos][moveZpos]) {
                        map[moveXpos][moveYpos][moveZpos] = 1;
                        checked[moveXpos][moveYpos][moveZpos] = true;
//                        System.out.println(moveXpos + " , " + moveYpos + " , " + moveZpos);
                        queue.offer(new Position(moveXpos, moveYpos, moveZpos, now.count + 1));
                        if (now.count + 1 > result) result = now.count + 1;
                    }
                }
            }
        }
    }

    private static class Position {
        int x;
        int y;
        int z;
        int count;

        public Position(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }
}