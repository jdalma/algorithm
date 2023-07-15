package baekjoon.dijkstra;

import java.util.*;

class 합승택시요금 {
    private final List<List<Node>> list = new ArrayList<>();
    private int n;
    public static void main(String[] args) {
        합승택시요금 test = new 합승택시요금();
        test.solution(6 , 4 , 6 , 2 , new int[][]{
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        });
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        int answer = Integer.MAX_VALUE;
        for(int i = 0 ; i <= n ; i++) list.add(new ArrayList<>());
        for(int i = 0 ; i < fares.length ; i++){
            int node1 = fares[i][0];
            int node2 = fares[i][1];
            int cost = fares[i][2];
            list.get(node1).add(new Node(node2 , cost));
            list.get(node2).add(new Node(node1 , cost));
        }
        int[] costArr1 = new int[n + 1];
        int[] costArr2 = new int[n + 1];
        int[] costArr3 = new int[n + 1];
        dijkstra(costArr1 , a);
        dijkstra(costArr2 , b);
        dijkstra(costArr3 , s);
        Arrays.stream(costArr1).forEach(e -> System.out.print(e + " "));
        System.out.println();
        Arrays.stream(costArr2).forEach(e -> System.out.print(e + " "));
        System.out.println();
        Arrays.stream(costArr3).forEach(e -> System.out.print(e + " "));
        System.out.println();
        for(int i = 1; i <= n ; i ++) answer = Math.min(answer, costArr1[i] + costArr2[i] + costArr3[i]);
        return answer;
    }

    public void dijkstra(int[] costArr , int start){
        Arrays.fill(costArr , Integer.MAX_VALUE);
        costArr[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(start , 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowVertex = now.vertex;
            int nowCost = now.cost;
            for(Node moveNode : list.get(nowVertex)){
                int sumCost = nowCost + moveNode.cost;
                if(costArr[moveNode.vertex] < sumCost) continue;
                costArr[moveNode.vertex] = sumCost;
                pq.offer(new Node(moveNode.vertex, sumCost));
            }
        }
    }

    class Node implements Comparable<Node>{
        int vertex;
        int cost;
        public Node(int vertex , int cost){
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", cost=" + cost +
                    '}';
        }
    }
}
