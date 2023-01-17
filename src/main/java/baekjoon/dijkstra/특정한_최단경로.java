package baekjoon.dijkstra;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * > 📌 [풀이 출처](https://steady-coding.tistory.com/82)
 * > - 언뜻 보면, 다익스트라 알고리즘을 어떻게 응용해야할 지 어려울 수 있으나 쉽게 생각하면 금방 해결됩니다.
 * > - 바로 쪼개서 생각하는 것이죠.
 * > - 원래 거쳐야하는 노드가 없다면, 1 -> N까지 다이렉트로 다익스트라 알고리즘을 1번만 실행하면 됩니다.
 * > - 하지만, 거쳐야하는 노드가 있다면 아래와 같이 2가지 경우로 나누어서 생각해야 합니다.
 * > - (이 때, 거쳐야하는 노드를 v1과 v2로 표현하겠습니다.)
 * > - (1) 1 -> v1 -> v2 -> N
 * > - (2) 1 -> v2 -> v1 -> N
 * > - 1번 케이스의 경우, 1에서 v1까지 다익스트라 알고리즘을 수행, v1에서 v2까지 다익스트라 알고리즘을 수행, N까지 다익스트라 알고리즘을 수행하면 됩니다. 그리고 각각의 최단 거리에 대해서 모두 더합니다.
 * > - 2번 케이스도 마찬가지로 최단 거리를 더하고, 1번 케이스에서 구한 결과값과 비교해서 더 작은 값을 정답으로 출력하면 됩니다.
 * > - 다만, 1번과 2번 케이스에서 얻어낸 값이 INF보다 크거나 같다면 v1과 v2를 거쳐서 N에 도달할 수 없다는 의미이므로 -1을 출력하면 됩니다.
 */

class 특정한_최단경로 {
    static List<List<Edge>> list = new ArrayList<>();
    static int[] costArr;
    static boolean[] visited;
    static int vertex , edge;
    static int goThroughCity1 , goThroughCity2;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertex = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        costArr = new int[vertex + 1];
        visited = new boolean[vertex + 1];
        for(int i = 0 ; i <= vertex ; i++){
            list.add(new ArrayList<Edge>());
        }

        for(int i = 0 ; i < edge ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(start).add(new Edge(end , cost));
            list.get(end).add(new Edge(start , cost));
        }
        st = new StringTokenizer(br.readLine());
        goThroughCity1 = Integer.parseInt(st.nextToken());
        goThroughCity2 = Integer.parseInt(st.nextToken());

        // (1) 1 -> v1 -> v2 -> N
        int firstPath = 0;
        firstPath += solution(1 , goThroughCity1);
        firstPath += solution(goThroughCity1 , goThroughCity2);
        firstPath += solution(goThroughCity2 , vertex);

        // (2) 1 -> v2 -> v1 -> N
        int secondPath = 0;
        secondPath += solution(1 , goThroughCity2);
        secondPath += solution(goThroughCity2 , goThroughCity1);
        secondPath += solution(goThroughCity1 , vertex);

        System.out.println(Math.min(firstPath , secondPath));

    }

    public static int solution(int start , int end){
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        Arrays.fill(costArr, INF);
        Arrays.fill(visited , false);
        costArr[start] = 0;

        queue.offer(new Edge(start , 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            int stanVertex = now.node;
            if(!visited[stanVertex]){
                visited[stanVertex] = true;
                for(Edge newEdge : list.get(stanVertex)){
                    if(!visited[newEdge.node] && costArr[newEdge.node] > costArr[stanVertex] + newEdge.cost){
                        costArr[newEdge.node] = costArr[stanVertex] + newEdge.cost;
                        queue.offer(new Edge(newEdge.node , costArr[newEdge.node]));
                    }
                }
            }
        }
        if(costArr[end] == INF){
            System.out.println("-1");
            System.exit(0);
        }
        return costArr[end];
    }

    public static class Edge implements Comparable<Edge>{
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
        @Override
        public String toString() {
            return "Edge [node=" + node + ", cost=" + cost + "]";
        }
    }
}
