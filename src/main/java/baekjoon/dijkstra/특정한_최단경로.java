package baekjoon.dijkstra;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * > ๐ [ํ์ด ์ถ์ฒ](https://steady-coding.tistory.com/82)
 * > - ์ธ๋ป ๋ณด๋ฉด, ๋ค์ต์คํธ๋ผ ์๊ณ ๋ฆฌ์ฆ์ ์ด๋ป๊ฒ ์์ฉํด์ผํ  ์ง ์ด๋ ค์ธ ์ ์์ผ๋ ์ฝ๊ฒ ์๊ฐํ๋ฉด ๊ธ๋ฐฉ ํด๊ฒฐ๋ฉ๋๋ค.
 * > - ๋ฐ๋ก ์ชผ๊ฐ์ ์๊ฐํ๋ ๊ฒ์ด์ฃ .
 * > - ์๋ ๊ฑฐ์ณ์ผํ๋ ๋ธ๋๊ฐ ์๋ค๋ฉด, 1 -> N๊น์ง ๋ค์ด๋ ํธ๋ก ๋ค์ต์คํธ๋ผ ์๊ณ ๋ฆฌ์ฆ์ 1๋ฒ๋ง ์คํํ๋ฉด ๋ฉ๋๋ค.
 * > - ํ์ง๋ง, ๊ฑฐ์ณ์ผํ๋ ๋ธ๋๊ฐ ์๋ค๋ฉด ์๋์ ๊ฐ์ด 2๊ฐ์ง ๊ฒฝ์ฐ๋ก ๋๋์ด์ ์๊ฐํด์ผ ํฉ๋๋ค.
 * > - (์ด ๋, ๊ฑฐ์ณ์ผํ๋ ๋ธ๋๋ฅผ v1๊ณผ v2๋ก ํํํ๊ฒ ์ต๋๋ค.)
 * > - (1) 1 -> v1 -> v2 -> N
 * > - (2) 1 -> v2 -> v1 -> N
 * > - 1๋ฒ ์ผ์ด์ค์ ๊ฒฝ์ฐ, 1์์ v1๊น์ง ๋ค์ต์คํธ๋ผ ์๊ณ ๋ฆฌ์ฆ์ ์ํ, v1์์ v2๊น์ง ๋ค์ต์คํธ๋ผ ์๊ณ ๋ฆฌ์ฆ์ ์ํ, N๊น์ง ๋ค์ต์คํธ๋ผ ์๊ณ ๋ฆฌ์ฆ์ ์ํํ๋ฉด ๋ฉ๋๋ค. ๊ทธ๋ฆฌ๊ณ  ๊ฐ๊ฐ์ ์ต๋จ ๊ฑฐ๋ฆฌ์ ๋ํด์ ๋ชจ๋ ๋ํฉ๋๋ค.
 * > - 2๋ฒ ์ผ์ด์ค๋ ๋ง์ฐฌ๊ฐ์ง๋ก ์ต๋จ ๊ฑฐ๋ฆฌ๋ฅผ ๋ํ๊ณ , 1๋ฒ ์ผ์ด์ค์์ ๊ตฌํ ๊ฒฐ๊ณผ๊ฐ๊ณผ ๋น๊ตํด์ ๋ ์์ ๊ฐ์ ์ ๋ต์ผ๋ก ์ถ๋ ฅํ๋ฉด ๋ฉ๋๋ค.
 * > - ๋ค๋ง, 1๋ฒ๊ณผ 2๋ฒ ์ผ์ด์ค์์ ์ป์ด๋ธ ๊ฐ์ด INF๋ณด๋ค ํฌ๊ฑฐ๋ ๊ฐ๋ค๋ฉด v1๊ณผ v2๋ฅผ ๊ฑฐ์ณ์ N์ ๋๋ฌํ  ์ ์๋ค๋ ์๋ฏธ์ด๋ฏ๋ก -1์ ์ถ๋ ฅํ๋ฉด ๋ฉ๋๋ค.
 */

class ํน์ ํ_์ต๋จ๊ฒฝ๋ก {
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
