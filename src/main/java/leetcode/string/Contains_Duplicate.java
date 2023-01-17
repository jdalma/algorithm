package leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Contains_Duplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer , Integer> map = new HashMap<Integer , Integer>();
        for(int num : nums){
            int count = map.getOrDefault(num , 0);
            if(count > 0) return true;
            map.put(num , count + 1);
        }
        return false;
    }

    /**
     * Set 사용
     */
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int num : nums){
            if(set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }

    /**
     * Best -> 정렬 사용
     */
    public boolean containsDuplicate3(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length - 1 ; i++){
            if(nums[i] == nums[i + 1]){
                return true;
            }
        }
        return false;
    }
}