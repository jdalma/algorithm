package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

class 서강그라운드 {
    static int[] nodeItemArr;
    static int nodeCount , searchScope , edgeCount;
    static List<List<Edge>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCount = Integer.parseInt(st.nextToken());
        searchScope = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        nodeItemArr = new int[nodeCount + 1];

        // 각 구역의 아이템 수
        // list 구역의 수 만큼 추가
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i <= nodeCount ; i++){
            if(i != 0) nodeItemArr[i] = Integer.parseInt(st.nextToken());
            list.add(new ArrayList<>());
        }

        // 간선
        for(int i = 0 ; i < edgeCount ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Edge(end , cost));
            list.get(end).add(new Edge(start , cost));
        }

        int result = 0;
        for(int i = 1 ; i <= nodeCount ; i++){
            result = Math.max(result , dijkstra(i));
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int startNode){
        int[] costArr = new int[nodeCount + 1];
        Arrays.fill(costArr , Integer.MAX_VALUE);

        costArr[startNode] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(startNode , 0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();
            int nowNode = now.node;
            int nowCost = now.cost;
            for(Edge near : list.get(nowNode)){
                int nearNode = near.node;
                int nearCost = near.cost;
                int sumCost = nowCost + nearCost;
                if(costArr[nearNode] > sumCost){
                    costArr[nearNode] = sumCost;
                    pq.offer(new Edge(nearNode , sumCost));
                }
            }
        }

        int result = 0;
        for(int i = 0 ; i < costArr.length ; i++){
            if(costArr[i] <= searchScope) result += nodeItemArr[i];
        }
        return result;
    }

    static class Edge implements Comparable<Edge>{
        int node;
        int cost;
        public Edge(int node , int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

}