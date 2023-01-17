package baekjoon.bfs;

import java.io.*;
import java.util.*;

class 인구이동 {
    static int PEOPLE_DIFF_MAX , PEOPLE_DIFF_MIN , size;
    static int[][] map;
    static int[][] moves4dir = { {-1 , 0} , {0 , 1} , {1 , 0} , {0 , -1} };
    static int[][] checked;
    static boolean[][] visited;
    static boolean markFlag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        PEOPLE_DIFF_MIN = Integer.parseInt(st.nextToken());
        PEOPLE_DIFF_MAX = Integer.parseInt(st.nextToken());

        map = new int[size][size];

        // 입력
        for(int i = 0 ; i < size ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < size ; j++){
                int cell = Integer.parseInt(st.nextToken());
                map[i][j] = cell;
            }
        }

        int result = 0;
        while(true){
            // 1번에서 행하는 연합이 만들어 졌다면 true이다.
            markFlag = false;

            // 연합의 순번이(key) 기록된다
            checked = new int[size][size];

            // 연합을 구할 때 방문처리용
            visited = new boolean[size][size];

            // 1.
            // 인구이동이 가능한 영역을 checked에 기록한다.
            // checked에는 각 연합마다 구분되게 기록된다. (key를 1씩 증가시킨다)
            moveCheck();
            if(markFlag) result++;
            else break;

            // 2. checked에 기록된 key에 속하는 연합을 찾아 평균을 구하여 map에 기록한다.
            for(int i = 0 ; i < size ; i++){
                for(int j = 0 ; j < size ; j++){
                    if(checked[i][j] != 0){
                        bfs(i , j , checked[i][j]);
                    }
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs(int x , int y , int key){
        // BFS가 끝난 후 평균을 넣어줄 위치들
        List<Position> positions = new ArrayList<>();
        Queue<Position> queue = new ArrayDeque<>();

        Position startPos = new Position(x , y);

        positions.add(startPos);
        queue.offer(new Position(x , y));
        checked[x][y] = 0;

        int sumPeoples = map[x][y];

        while(!queue.isEmpty()){
            Position now = queue.poll();
            for(int[] move : moves4dir){
                int moveX = now.x + move[0];
                int moveY = now.y + move[1];
                if(moveX >= 0 && moveX < size && moveY >= 0 && moveY < size){
                    if(checked[moveX][moveY] == key){
                        Position movePos = new Position(moveX , moveY);
                        checked[moveX][moveY] = 0;
                        sumPeoples += map[moveX][moveY];
                        queue.offer(movePos);
                        positions.add(movePos);
                    }
                }
            }
        }

        int avg = sumPeoples / positions.size();
        for(Position pos : positions){
            map[pos.x][pos.y] = avg;
        }
    }

    public static void moveCheck(){
        int key = 0;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
                    markKey(i , j , ++key);
                }
            }
        }
    }

    public static void markKey(int x , int y , int key){
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(x , y));

        while(!queue.isEmpty()){
            Position now = queue.poll();
            int peoples = map[now.x][now.y];
            for(int[] move : moves4dir){
                int moveX = now.x + move[0];
                int moveY = now.y + move[1];
                if(moveX >= 0 && moveX < size && moveY >= 0 && moveY < size && !visited[moveX][moveY]){
                    int diff = Math.abs(peoples - map[moveX][moveY]);
                    if(PEOPLE_DIFF_MIN <= diff && diff <= PEOPLE_DIFF_MAX){
                        visited[moveX][moveY] = true;
                        checked[now.x][now.y] = key;
                        checked[moveX][moveY] = key;
                        markFlag = true;

                        queue.offer(new Position(moveX , moveY));
                    }
                }
            }
        }
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