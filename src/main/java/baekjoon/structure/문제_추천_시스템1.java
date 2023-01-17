package baekjoon.structure;

import java.io.*;
import java.util.*;

class 문제_추천_시스템1 {
    static Map<Integer , Integer> problems = new HashMap<>();
    static List<List<Integer>> levels = new ArrayList<>();
    //    static PriorityQueue<Problem> asc = new PriorityQueue<>(Comparator.comparing(Problem::getLevel).thenComparing(Problem::getNo));
//    static PriorityQueue<Problem> desc = new PriorityQueue<>(Comparator.comparing(Problem::getLevel).thenComparing(Problem::getNo).reversed());
    static TreeSet<Problem> treeSet = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int problemCount = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < 101 ; i++) levels.add(new LinkedList<>());

        StringTokenizer st;
        for(int i = 0 ; i < problemCount ; i++){
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            problems.put(no , level);
            treeSet.add(new Problem(no , level));
        }

        int commandCount = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < commandCount ; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int firstParam = Integer.parseInt(st.nextToken());
            switch(command){
                case "recommend" :
                    bw.append(recommend(firstParam) + "\n");
                    break;
                case "add" :
                    int level = Integer.parseInt(st.nextToken());
                    problems.put(firstParam , level);
                    treeSet.add(new Problem(firstParam , level));
                    break;
                case "solved" :
                    treeSet.remove(new Problem(firstParam , problems.get(firstParam)));
                    problems.remove(firstParam);
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static String recommend(int param){
        int no;
        if (param > 0) {
            no = treeSet.last().no;
        } else {
            no = treeSet.first().no;
        }
        return String.valueOf(no);
    }

    static class Problem implements Comparable<Problem>{
        int no;
        int level;

        public Problem(int no , int level) {
            this.no = no;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.level == o.level){
                return this.no - o.no;
            }
            return this.level - o.level;
        }

        @Override
        public String toString() {
            return "Problem [no=" + no + ", level=" + level + "]";
        }

    }
}