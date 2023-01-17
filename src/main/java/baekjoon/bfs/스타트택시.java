package baekjoon.bfs;

import java.io.*;
import java.util.*;

/**
 * - 택시는 승객을 태우러 가거나 승객이 원하는 목적지로 이동할 때 마다 1의 연료를 소비한다.
 *   - 승객을 태우고 해당 목적지에 도착하면 목적지에 도달할 때 까지 소모한 연료의 2배를 보충한다.
 * - 여러 명의 승객이 같은 거리에 있다면 행 번호가 작은 것 부터 탑승 시키고 , 행 번호 마저 같다면 열 번호가 작은 승객을 탑승 시킨다.
 * - 승객이 무조건 도착지를 간다는 보장이 없다.
 * - 시작점에 승객이 있거나 , 승객의 도착지에 다른 승객의 출발지가 될 수 있다.
 * - 승객의 출발지는 다 다르지만 도착지는 종북될 수 있다.
 * - 시뮬레이션 문제 굉장히 고생했다... 함정과 반례가 너무 많았다... 지문을 잘 읽고 문제를 잘 분석하고 반례를 잘 생각했어야했다
 */

class 스타트택시 {
    static int mapSize, customerCount , fuel , startX , startY;
    static int customerX , customerY , customerD , customerKey;
    static int[][] map , dist;
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , -1 , 0 , 1};
    static boolean[][] visited;
    static Map<Integer , Position> destinations = new HashMap<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        mapSize = Integer.parseInt(st.nextToken());
        customerCount = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];

        // map 초기화
        for(int i = 0 ; i < mapSize ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < mapSize; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작 위치
        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;

        // 손님 , 목적지 위치
        for(int i = 1 ; i <= customerCount ; i++){
            st = new StringTokenizer(br.readLine());
            int customerXpos = Integer.parseInt(st.nextToken()) - 1;
            int customerYpos = Integer.parseInt(st.nextToken()) - 1;
            int destinationXpos = Integer.parseInt(st.nextToken()) - 1;
            int destinationYpos = Integer.parseInt(st.nextToken()) - 1;

            // 손님의 위치는 map배열에서 1000 + i로 지정한다.
            map[customerXpos][customerYpos] = 1000 + i;
            // 목적지
            destinations.put(i, new Position(destinationXpos , destinationYpos));
        }
        for(int main = 1 ; main <= customerCount ; main++){
            // 현재 위치 기준 가장 가까운 손님 찾기
            findCustomer();

            // 목적지 출발
            if(!goDestination()){
                fuel = -1;
                SystemExit();
            }
        }

        SystemExit();
    }

    public static void findCustomer() throws IOException {
        initArray();

        // 손님 위치 초기화
        customerX = customerY = customerD = Integer.MAX_VALUE;

        // 현재 위치에 손님이 있을 경우
        if(map[startX][startY] > 1000){
            setCustomerInfo(startX , startY);
            customerD = 0;
            map[customerX][customerY] = 0;
            return;
        }
        Queue<Position> queue = new LinkedList<>();
        visited[startX][startY] = true;
        dist[startX][startY] = 0;
        queue.offer(new Position(startX , startY));

        while(!queue.isEmpty()){
            Position now = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = now.x + moveX[i];
                int moveYpos = now.y + moveY[i];
                if(moveXpos >= 0 && moveXpos < mapSize && moveYpos >= 0 && moveYpos < mapSize){
                    if(map[moveXpos][moveYpos] != 1 && !visited[moveXpos][moveYpos] && dist[moveXpos][moveYpos] > dist[now.x][now.y] + 1){
                        dist[moveXpos][moveYpos] = dist[now.x][now.y] + 1;
                        visited[moveXpos][moveYpos] = true;
                        queue.offer(new Position(moveXpos , moveYpos));
                        // 1000보다 크다면 손님
                        if(map[moveXpos][moveYpos] > 1000){
                            // 손님 정보 갱신
                            // 현재 거리 비용 보다 더 작은 손님이 위치해 있다면 손님 정보 갱신
                            if(customerD > dist[moveXpos][moveYpos]){
                                setCustomerInfo(moveXpos , moveYpos);
                            }
                            else if(customerD == dist[moveXpos][moveYpos]){
                                // 같은 거리 비용이면 행이 더 작은 손님 갱신
                                if(moveXpos < customerX){
                                    setCustomerInfo(moveXpos , moveYpos);
                                }
                                else if(moveXpos == customerX && moveYpos < customerY){
                                    // 행은 같고 열이 작다면 더 작은 손님 갱신
                                    setCustomerInfo(moveXpos , moveYpos);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 연료 갱신
        fuel -= customerD;
        if(customerX == Integer.MAX_VALUE || customerY == Integer.MAX_VALUE || fuel <= 0){
            fuel = -1;
            SystemExit();
        }
        // 최단 거리 손님을 찾았다면 해당 위치 제거
        map[customerX][customerY] = 0;
    }

    public static boolean goDestination() throws IOException {

        initArray();

        dist[customerX][customerY] = 0;
        visited[customerX][customerY] = true;

        Queue<Position> queue = new LinkedList<>();
        Position destination = destinations.get(customerKey);
        queue.offer(new Position(customerX , customerY));

        while(!queue.isEmpty()){
            Position now = queue.poll();
            // 연료가 다 떨어지면 종료
            if(dist[now.x][now.y] > fuel) {
                return false;
            }
            // 목적지 도달 시
            else if(destination.isSame(now.x , now.y)){
                fuel = (dist[now.x][now.y]) * 2 + (fuel - dist[now.x][now.y]);
                startX = now.x;
                startY = now.y;
                return true;
            }
            for(int i = 0 ; i < 4 ; i++){
                int moveXpos = now.x + moveX[i];
                int moveYpos = now.y + moveY[i];
                if(moveXpos >= 0 && moveXpos < mapSize && moveYpos >= 0 && moveYpos < mapSize){
                    if(map[moveXpos][moveYpos] != 1 && !visited[moveXpos][moveYpos]){
                        dist[moveXpos][moveYpos] = dist[now.x][now.y] + 1;
                        visited[moveXpos][moveYpos] = true;
                        queue.offer(new Position(moveXpos , moveYpos));
                    }
                }
            }
        }
        return false;
    }

    public static void setCustomerInfo(int x , int y){
        customerX = x;
        customerY = y;
        customerD = dist[x][y];
        customerKey = map[x][y] - 1000;
    }

    public static void initArray(){
        // 방문 체크 배열 초기화
        visited = new boolean[mapSize][mapSize];

        // 거리 배열 초기화
        dist = new int[mapSize][mapSize];
        for(int i = 0 ; i < mapSize ; i++){
            Arrays.fill(dist[i] , Integer.MAX_VALUE);
        }
    }

    public static void SystemExit() throws IOException {
        bw.append(String.valueOf(fuel));
        bw.flush();
        bw.close();
        br.close();
        System.exit(0);
    }

    static class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean isSame(int x , int y){
            return this.x == x && this.y == y;
        }
    }
}
