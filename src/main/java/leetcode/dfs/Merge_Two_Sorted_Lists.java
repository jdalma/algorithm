package leetcode.dfs;

import leetcode.ListNode;

class Merge_Two_Sorted_Lists {
    /**
     * `Time complexity : O(n + m) , Space complexity : O(n + m)`
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        else if(list2 == null) return list1;
        else if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next , list2);
            return list1;
        }
        else{
            list2.next = mergeTwoLists(list1 , list2.next);
            return list2;
        }
    }

    /**
     * `Time complexity : O(n + m) , Space complexity : O(1)`
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode returnList = new ListNode(-1);
        ListNode tmp = returnList;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                tmp.next = list1;
                list1 = list1.next;
            }
            else{
                tmp.next = list2;
                list2 = list2.next;
            }
            tmp = tmp.next;
        }

        tmp.next = list1 == null ? list2 : list1;
        return returnList.next;
    }
}