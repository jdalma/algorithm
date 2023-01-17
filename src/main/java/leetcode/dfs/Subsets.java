package leetcode.dfs;

import java.util.*;

/**
 * - 내가 기존에 해왔던 방식과 다르게 **조합의 리스트에서 마지막 1개씩을 삭제하며 반복문의 글로벌 변수(`k`)와 똑같을 때만 새로운 리스트를 반환하게 돼있다.**
 */

class Subsets {
    List<List<Integer>> output = new ArrayList();
    int n, k;

    public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        System.out.println("curr.size() : " + curr.size() + ", k : " + k);
        // if the combination is done
        if (curr.size() == k) {
            System.out.println("{" + first + "} return : " + curr);
            output.add(new ArrayList(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            // System.out.print("{" + first + "}  before remove : " + curr);
            curr.remove(curr.size() - 1);
            // System.out.println("  after remove : " + curr);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; k++) {
            System.out.println("------------------- start [" + k + "]--------------");
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }
}