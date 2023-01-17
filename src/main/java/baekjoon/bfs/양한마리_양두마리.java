package baekjoon.bfs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class 양한마리_양두마리 {
    static int[][] moves4dir = { {-1 , 0} , {0 , 1} , {1 , 0} , {0 , -1} };
    static int row , col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for(int test = 0 ; test < testCase ; test++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            char[][] map = new char[row][col];
            boolean[][] visited = new boolean[row][col];

            for(int i = 0 ; i < row ; i++){
                map[i] = br.readLine().toCharArray();
            }

            int chunkCount = 0;
            for(int i = 0 ; i < row ; i++){
                for(int j = 0 ; j < col ; j++){
                    if(map[i][j] == '#'){
                        // 양 무리 방문 처리
                        check(i , j , map);
                        chunkCount++;
                    }
                }
            }

            bw.append(chunkCount + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void check(int x , int y , char[][] map){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{ x , y});
        map[x][y] = '.';

        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int[] pos : moves4dir){
                int moveX = now[0] + pos[0];
                int moveY = now[1] + pos[1];
                if(moveX >= 0 && moveX < row && moveY >= 0 && moveY < col){
                    if(map[moveX][moveY] == '#'){
                        q.offer(new int[] {moveX , moveY});
                        map[moveX][moveY] = '.';
                    }
                }
            }
        }
    }
}
