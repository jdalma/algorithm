package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 촌수계산 {
    static List<List<Integer>> list = new ArrayList<>();
    static int start , target;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        checked = new boolean[people + 1];
        for(int i = 0 ; i < people + 1; i++) list.add(new ArrayList<>());

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < count ; i++){
            st = new StringTokenizer(br.readLine());
            int people1 = Integer.parseInt(st.nextToken());
            int people2 = Integer.parseInt(st.nextToken());
            list.get(people1).add(people2);
            list.get(people2).add(people1);
        }
        bfs(start);
        System.out.println("-1");
    }
    public static void bfs(int start){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start , 0});
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int people = now[0];
            int count = now[1];
            if(people == target){
                System.out.println(count);
                System.exit(0);
            }
            else {
                for(int relationalPeople : list.get(people)){
                    if(!checked[relationalPeople]){
                        checked[relationalPeople] = true;
                        queue.offer(new int[]{relationalPeople , count + 1});
                    }
                }
            }
        }
    }
}