package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 트리의_부모_찾기 {
    static int[] printArr;
    static List<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        printArr = new int[size + 1];
        for(int i = 0 ; i < size + 1 ; i++) list.add(new ArrayList<Integer>());
        for(int i = 0 ; i < size - 1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            list.get(parent).add(child);
            list.get(child).add(parent);
        }

        dfs(1);
        for(int i = 2 ; i < printArr.length ; i++){
            System.out.println(printArr[i]);
        }
    }

    public static void dfs(int parent){
        for(int value : list.get(parent)){
//            System.out.println(value);
            if(printArr[value] == 0){
                printArr[value] = parent;
                dfs(value);
            }
        }
    }
}