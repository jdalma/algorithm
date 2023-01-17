package baekjoon.dfs;

import java.io.*;
import java.util.*;

class ABCDE {
    static List<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int peopleCount = Integer.parseInt(st.nextToken());
        int relationCount = Integer.parseInt(st.nextToken());
        checked = new boolean[peopleCount];
        for(int i = 0 ; i < peopleCount ; i++) list.add(new ArrayList<Integer>());
        for(int i = 0 ; i < relationCount ; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            list.get(first).add(second);
            list.get(second).add(first);
        }
        for(int i = 0 ; i < list.size() ; i++){
            Arrays.fill(checked , false);
            checked[i] = true;
            dfs(i , 0);
        }
        System.out.println("0");
    }
    public static void dfs(int start , int depth){
        if(depth >= 4){
            System.out.println("1");
            System.exit(0);
        }
        else{
            for(int relationPeople : list.get(start)){
                if(!checked[relationPeople]){
                    checked[relationPeople] = true;
//                    System.out.println(start + " -> " + relationPeople + " : " + (depth + 1) );
                    dfs(relationPeople , depth + 1);
                    checked[relationPeople] = false;
                }
            }
        }
    }
}