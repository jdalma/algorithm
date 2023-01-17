package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 상범빌딩 {
    static char[][][] arr;
    static boolean[][][] visited;
    static Position start , end;
    // 업 , 상 , 우 , 하 , 좌 , 다운
    static int[] moveZ = {1 , 0 , 0 , 0 , 0 , -1};
    static int[] moveX = {0 , -1 , 0 , 1 , 0 , 0};
    static int[] moveY = {0 , 0 , 1 , 0 , -1 , 0};
    static int z , x , y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());

            z = Integer.parseInt(st.nextToken()); // 높이
            x = Integer.parseInt(st.nextToken()); // 행
            y = Integer.parseInt(st.nextToken()); // 열
            if(z == 0 && x == 0 && y == 0){
                break;
            }
            arr = new char[z][x][y];
            visited = new boolean[z][x][y];
            for(int i = 0 ; i < z ; i++){
                for(int j = 0 ; j < x ; j++){
                    char[] line = br.readLine().toCharArray();
                    if(line.length == 0) line = br.readLine().toCharArray();
                    for(int k = 0 ; k < y ; k++){
                        if(line[k] == 'S'){
                            start = new Position(i , j , k , 0);
                        }
                        else if(line[k] == 'E'){
                            end = new Position(i , j , k , 0);
                        }
                        arr[i][j][k] = line[k];
                    }
                }
            }
            dfs();
        }
    }
    public static void dfs(){
        boolean findFlag = false;
        Queue<Position> positions = new LinkedList<>();
        positions.offer(start);
        while (!positions.isEmpty()){
            Position pos = positions.poll();
            if(end.equals(pos)){
                System.out.println("Escaped in " + pos.moveCount + " minute(s).");
                findFlag = true;
                break;
            }
            for(int i = 0 ; i < 6 ; i++){
                int moveZPos = pos.z + moveZ[i];
                int moveXPos = pos.x + moveX[i];
                int moveYPos = pos.y + moveY[i];
                if(moveZPos >= 0 && moveXPos >= 0 && moveYPos >= 0 && moveZPos < z && moveXPos < x && moveYPos < y){
                    if(!visited[moveZPos][moveXPos][moveYPos] && arr[moveZPos][moveXPos][moveYPos] != '#'){
                        visited[moveZPos][moveXPos][moveYPos] = true;
                        positions.offer(new Position(moveZPos , moveXPos , moveYPos , pos.moveCount + 1));
                    }
                }
            }
        }
        if(!findFlag) System.out.println("Trapped!");
    }

    private static class Position{
        int x;
        int y;
        int z;
        int moveCount;
        public Position(int z, int x, int y , int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.moveCount = count;
        }
        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (this == o) return true;
            Position position = (Position) o;

            if (x != position.x) return false;
            if (y != position.y) return false;
            return z == position.z;
        }
    }
}