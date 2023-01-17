package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - 일반 **BFS**문제지만 `memoization` 때문에 고생
 * > - 📌 [출처](https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-14226-%EC%9D%B4%EB%AA%A8%ED%8B%B0%EC%BD%98-JAVA)
 * > - '모든' 상태에 대해 BFS를 실행한다면 이미 확인한 상태를 다시 확인하는 경우가 생긴다. `화면의 이모티콘 개수가 4이고 클립보드에 있는 이모티콘 개수가 2이고 시간은 4인 상태` 가 있다고 해보자.
 * > - 여기서 **1번 붙혀넣고 삭제를 2번 하는 작업을 수행**했다면, `화면의 이모티콘 개수가 4이고 클립보드에 있는 이모티콘 개수가 2이고 시간은 7인 상태` 가 된다.
 * > - **화면과 클립보드의 이모티콘 개수는 같지만 시간은 늘어나있다.**
 * > - **이것처럼 이미 확인했던 [화면 이모티콘 개수][클립보드의 이모티콘 개수]의 쌍을 다시 본다면 시간이 늘어나기 때문에 최솟값을 찾을 수 없다.**
 */
class Main {
    static List<ArrayList<Integer>> list;
    static boolean[][] checked = new boolean[2001][2001];
    static StringBuilder resultString = new StringBuilder();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        System.out.println(solve(target));
    }

    private static int solve(int target) {
        Queue<Emoticon> queue = new LinkedList<Emoticon>();
        queue.offer(new Emoticon(1 , 0 , 0));
        while(!queue.isEmpty()) {
            Emoticon now = queue.poll();
//            System.out.println(now);
            if(now.emoCount == target) return now.time;
            Emoticon after;
            if(now.emoCount >= 0 && now.emoCount < 1001) {
                if(!checked[now.emoCount][now.emoCount]) {
                    after = new Emoticon(now.emoCount , now.time + 1 , now.emoCount);
                    queue.offer(after);
                    checked[now.emoCount][now.emoCount] = true;
//                    System.out.println("copy - " + after);
                }
                if(!checked[now.emoCount + now.clipboard][now.clipboard]) {
                    after = new Emoticon(now.emoCount + now.clipboard , now.time + 1 , now.clipboard);
                    queue.offer(after);
                    checked[now.emoCount + now.clipboard][now.clipboard] = true;
//                    System.out.println("paste - " + after);
                }
                if(now.emoCount - 1 >= 1 && !checked[now.emoCount - 1][now.clipboard]) {
                    after = new Emoticon(now.emoCount - 1 , now.time + 1 , now.clipboard);
                    queue.offer(after);
                    checked[now.emoCount - 1][now.clipboard] = true;
//                    System.out.println("minus - " + after);
                }
            }
        }
        return 0;
    }

    private static class Emoticon{
        int emoCount;
        int time;
        int clipboard;
        public Emoticon(int emoCount, int time, int clipboard) {
            this.emoCount = emoCount;
            this.time = time;
            this.clipboard = clipboard;

        }
        @Override
        public String toString() {
            return "Emoticon [emoCount=" + emoCount + ", time=" + time + ", clipboard=" + clipboard + "]";
        }

    }
}
