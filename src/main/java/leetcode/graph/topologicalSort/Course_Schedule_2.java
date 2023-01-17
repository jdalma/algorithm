package leetcode.graph.topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * - [위상정렬 - 동빈나](https://m.blog.naver.com/ndb796/221236874984)
 * - 순서가 정해져 있는 작업을 할 때 그 순서를 결정해주기 위해 사용하는 알고리즘
 */

class Course_Schedule_2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>();
        int[] relation = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i++) list.add(new ArrayList<Integer>());
        for(int i = 0 ; i < prerequisites.length ; i++){
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            list.get(x).add(y);
            relation[y] += 1;
        }

        return topologySort(list , numCourses , relation);
    }

    private int[] topologySort(List<List<Integer>> list , int numCourses , int[] relation){
        int[] sortResult = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0 ; i < relation.length ; i++){
            if(relation[i] == 0){
                queue.offer(i);
            }
        }

        int index = numCourses - 1;
        for(int i = 0 ; i < numCourses ; i++){
            if(queue.isEmpty()){
                System.out.println("사이클 발생");
                return new int[0];
            }
            int value = queue.poll();
            System.out.println("nowNode : " + value);
            sortResult[index--] = value;
            for(int j = 0 ; j < list.get(value).size() ; j++){
                int nextNode = list.get(value).get(j);
                relation[nextNode] = relation[nextNode] - 1;
                if(relation[nextNode] == 0){
                    queue.offer(nextNode);
                }
            }
        }

        return sortResult;
    }
}