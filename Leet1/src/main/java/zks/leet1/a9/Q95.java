package zks.leet1.a9;


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q95 {
    /*
    95. 不同的二叉搜索树 II
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
示例 1：
输入：n = 3
输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
示例 2：
输入：n = 1
输出：[[1]]
提示：
1 <= n <= 8
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    //生成区间[l,r]的二叉搜索树集合
    public List<TreeNode> generateTrees(int l, int r) {
        List<TreeNode> ans = new ArrayList<>();
        if (l > r) {
            ans.add(null);
            return ans;
        }
        for (int i = l; i <= r; i++) {
            List<TreeNode> leftSubTree = generateTrees(l, i - 1);
            List<TreeNode> rightSubTree = generateTrees(i + 1, r);
            //左子树、右子树、根拼接成答案
            for (TreeNode lTree : leftSubTree) {
                for (TreeNode rTree : rightSubTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = lTree;
                    root.right = rTree;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
    @Test
    public void test(){
        List<TreeNode> treeNodes = generateTrees(2);
        System.out.println("over");
    }



}
