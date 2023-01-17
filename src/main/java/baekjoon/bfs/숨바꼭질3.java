package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - 원래 이 문제는 단순한 BFS를 요구하는 문제가 아닙니다.
 * - 왜냐하면, BFS를 하기 위해서는 모든 간선의 가중치가 동일해야 한다는 전제 조건이 필요하기 때문입니다.
 * - 이 문제는 가중치가 0인 간선이 있기 때문에 일반적으로는 단순한 BFS를 쓸 수 없으나, 문제의 특성 때문에 방문 순서에 따라서 단순 BFS로도 우연히도 항상 정답을 찾을 수 있을 뿐입니다.
 * - 왜 하필 이 순서로 하면 항상 정답이 나오는가를 증명하는 건 매우 복잡한 일입니다.
 *
 * - 이 문제를 보다 일반화된 경우 (가중치가 0인 간선이 있는 경우)에 대해 해결하려면, 즉, 이 문제의 의도대로 풀려면 다음과 같은 방법들을 사용할 수 있습니다.
 * 	- **다익스트라 알고리즘**
 * 	- **0-1 BFS** : 가중치가 0인 간선에 연결된 정점은 큐의 맨 뒤가 아닌 맨 앞에 넣는 방법
 * 	- `* 2`를 별도의 간선으로 생각하지 않고, +1이나 -1에 의한 좌표를 큐에 넣을 때 그 좌표의 2의 거듭제곱 배인 좌표들을 전부 큐에 넣는 방법
 * - [참고](https://www.acmicpc.net/board/view/38887#comment-69010)
 */

class 숨바꼭질3 {
    static int[] move = {1 , -1 , 2};
    static int[] distance;
    static int start , destination;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        distance = new int[100002];
        Arrays.fill(distance , Integer.MAX_VALUE);
        bfs();
    }

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {start , 0});
        distance[start] = 0;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            if(now[0] == destination){
                System.out.println(now[1]);
                System.exit(0);
            }
            for(int i = 2 ; i >= 0 ; i--){
                int movePos = i == 2 ? now[0] * move[i] : now[0] + move[i];
                int moveCnt = i == 2 ? now[1] : now[1] + 1;
                if(movePos >= 0 && movePos < 100002 && distance[movePos] > moveCnt){
                    distance[movePos] = moveCnt;
                    queue.offer(new int[] {movePos , moveCnt});
                }
            }
        }
    }
}