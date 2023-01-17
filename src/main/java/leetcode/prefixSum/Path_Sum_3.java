package leetcode.prefixSum;

import leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * - [1806번] 부분합 :: https://www.acmicpc.net/problem/1806
 * - [1644번] 소수의 연속합 ::  https://www.acmicpc.net/problem/1644
 * - [2015번] 수들의 합 4 ::  https://www.acmicpc.net/problem/2015
 * - [출처](https://www.crocus.co.kr/843)
 */

class Path_Sum_3 {
    int resultCount = 0;
    int target;
    Map<Integer , Integer> occurrenceMap = new HashMap<Integer,Integer>();

    public void dfs(TreeNode node , int sum){
        if(node != null){
            sum += node.val;
            if(sum == target) resultCount++;

            resultCount += occurrenceMap.getOrDefault(sum - target , 0);
            occurrenceMap.put(sum , occurrenceMap.getOrDefault(sum , 0) + 1);

            // System.out.println(sum + " " + (sum - target) + " -> " + occurrenceMap);

            dfs(node.left , sum);
            dfs(node.right , sum);

            occurrenceMap.put(sum , occurrenceMap.get(sum) - 1);
        }
    }

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        dfs(root , 0);
        return resultCount;
    }
}