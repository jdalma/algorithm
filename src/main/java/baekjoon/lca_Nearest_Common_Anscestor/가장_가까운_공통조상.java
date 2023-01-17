package baekjoon.lca_Nearest_Common_Anscestor;

import java.io.*;
import java.util.*;

class 가장_가까운_공통조상 {
    static List<List<Integer>> list;
    static boolean[] checked;
    static int[] parentArr , depthArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(br.readLine());

        for(int testCase = 0 ; testCase < testCaseCount ; testCase++){
            int nodeSize = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            checked = new boolean[nodeSize + 1];
            parentArr = new int[nodeSize + 1];
            depthArr = new int[nodeSize + 1];

            StringTokenizer st;
            for(int i = 0 ; i <= nodeSize ; i++) list.add(new ArrayList<>());
            for(int i = 0 ; i < nodeSize - 1; i++){
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                list.get(node1).add(node2);
                checked[node2] = true;
            }

            st = new StringTokenizer(br.readLine());
            int target1 = Integer.parseInt(st.nextToken());
            int target2 = Integer.parseInt(st.nextToken());

            recursive(findRoot() , 1 , 0);
            bw.write(LCA(target1 , target2));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static String LCA(int target1 , int target2){
        int target_1_depth = depthArr[target1];
        int target_2_depth = depthArr[target2];

        while(target_1_depth > target_2_depth){
            target1 = parentArr[target1];
            target_1_depth--;
        }

        while(target_2_depth > target_1_depth){
            target2 = parentArr[target2];
            target_2_depth--;
        }

        while(target1 != target2){
            target1 = parentArr[target1];
            target2 = parentArr[target2];
        }
        return target1 + "\n";
    }

    public static void recursive(int current , int depth , int parent){
        depthArr[current] = depth;
        parentArr[current] = parent;
        for(int nearNode : list.get(current)){
            recursive(nearNode , depth + 1 , current);
        }
    }

    public static int findRoot(){
        for(int i = 1 ; i < checked.length ; i++){
            if(!checked[i]) return i;
        }
        return 0;
    }
}
