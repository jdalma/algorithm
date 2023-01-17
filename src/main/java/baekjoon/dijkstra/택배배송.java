package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

class 택배배송 {
    static int node, edge;
    static List<List<Road>> roads = new ArrayList<>();
    static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        costs = new int[node + 1];

        // 인접 리스트 , 비용 배열 초기화
        for (int i = 0; i <= node; i++) {
            roads.add(new ArrayList<>());
            costs[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향 저장
            roads.get(start).add(new Road(end, cost));
            roads.get(end).add(new Road(start, cost));
        }

        dijkstra();

        bw.append(String.valueOf(costs[node]));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra() {
        // 우선순위 큐 사용
        // cost가 작은 것 부터 poll()
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));
        costs[1] = 0;

        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (costs[now.road] < now.cost)
                continue;
            for (Road nearRoad : roads.get(now.road)) {
                int sumCost = now.cost + nearRoad.cost;
                // 비용을 누적하며 나아감
                // 현재의 누적할 비용이 costs배열의 비용보다 작다면 큐에 담는다
                if (costs[nearRoad.road] > sumCost) {
                    costs[nearRoad.road] = sumCost;
                    pq.offer(new Road(nearRoad.road, sumCost));
                }
            }
        }
    }

    static class Road implements Comparable<Road> {
        int road;
        int cost;

        public Road(int road, int cost) {
            this.road = road;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost - o.cost;
        }
    }
}