package leetcode.graph;

import leetcode.TreeNode;

class Subtree_Of_Another_Tree {

    boolean answer = false;

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s.val == t.val && !answer){
            answer = DFS(s,t);
            if(answer) return answer;
        }

        if(s.left != null) isSubtree(s.left,t);
        if(s.right != null) isSubtree(s.right,t);

        return answer;
    }

    public boolean DFS(TreeNode s, TreeNode p){
        if(s == null && p == null) return true;
        if(s == null || p == null) return false;
        if(s.val != p.val) return false;
        return DFS(s.left,p.left) && DFS(s.right,p.right);
    }
}
