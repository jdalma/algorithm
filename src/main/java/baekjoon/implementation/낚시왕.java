package baekjoon.implementation;

import java.io.*;
import java.util.*;

class 낚시왕 {
    static int row, col, count, result = 0;
    static Shark[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        if (count > 0) {
            map = new Shark[row][col];

            for (int i = 0; i < count; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                map[x][y] = new Shark(s, d, z);
            }

            for (int people = 0; people < col; people++) {
                findShark(people);
                timePasses();
            }
        }

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void findShark(int y) {
        for (int x = 0; x < row; x++) {
            if (map[x][y] != null) {
                // 상어의 무게 누적
                result += map[x][y].z;
                map[x][y] = null;
                break;
            }
        }
    }

    public static void timePasses() {

        // 상어가 다 움직이고 나서 상어가 중첩되었는지 판단해야 하니 임시 배열 생성
        Shark[][] tmp = new Shark[row][col];

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (map[x][y] != null) {
                    move(x, y, map[x][y], tmp);
                }
            }
        }
        // 임시배열에 살아 있는 상어만 얕은 복사한다.
        copy(tmp);
    }

    public static void copy(Shark[][] tmp) {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                map[x][y] = tmp[x][y];
            }
        }
    }

    // 1
    // 4 3
    // 2
    public static void move(int x, int y, Shark shark, Shark[][] tmp) {
        int dir = shark.d;
        int distance = shark.s;

        int moveX = x;
        int moveY = y;

        // 움직일 거리가 0보다 클 때만 움직일 위치를 구한다.
        if (distance > 0) {
            if (dir == 1) {
                moveX = move(row, x, distance * -1, shark);
            } else if (dir == 2) {
                moveX = move(row, x, distance, shark);
            } else if (dir == 3) {
                moveY = move(col, y, distance, shark);
            } else {
                moveY = move(col, y, distance * -1, shark);
            }
        }

        if (tmp[moveX][moveY] != null && tmp[moveX][moveY].z > shark.z) {
            return;
        } else {
            tmp[moveX][moveY] = shark;
        }
    }

    public static int move(int range, int pos, int distance, Shark shark) {
        int count = Math.abs(distance);
        for (int i = 1; i <= count; i++) {
            if (distance > 0) {
                ++pos;
                if (range == pos) {
                    distance *= -1;
                    pos -= 2;
                    // 상어가 되돌아가면 방향을 바꿔준다.
                    shark.d = shark.d == 2 ? 1 : 4;
                }
            } else {
                --pos;
                if (-1 == pos) {
                    distance *= -1;
                    pos += 2;
                    // 상어가 되돌아가면 방향을 바꿔준다.
                    shark.d = shark.d == 1 ? 2 : 3;
                }
            }
        }
        return pos;
    }

    public static void print(Shark[][] m) {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (m[x][y] != null) {
                    System.out.println((x + 1) + " " + (y + 1) + " " + m[x][y]);
                }
            }
        }
    }

    static class Shark {
        int s, d, z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }
}
