package zks.leet1.a3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
39. 组合总和
给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。
示例 1：
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
示例 2：
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
示例 3：
输入: candidates = [2], target = 1
输出: []
提示：
1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都 互不相同
1 <= target <= 500
 */
public class Q39 {
    //考虑用递归穷举
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        recursion(candidates, target, list, ans, 0);
        return ans;
    }

    private void recursion(int[] candidates, int target, List<Integer> list, List<List<Integer>> ans, int index) {
        //递归基,list中所有元素的和大于target时证明失败,等于时是命中,这两种情况都不用再继续尝试了
        int sum = 0;
        for (Integer i : list) sum += i;
        if (sum > target) return;
        else if (sum == target) {
            ans.add(list);
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            List<Integer> l = new ArrayList<Integer>(list);
            l.add(candidates[i]);
            recursion(candidates, target, l, ans, i);
        }
    }

    @Test
    public void T39() {
        int[] candidates = {8,2, 3, 5};
        int target = 8;
        System.out.println(Arrays.toString(candidates));
        System.out.println(target);
        System.out.println(this.combinationSum(candidates, target));
    }
}
