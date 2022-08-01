package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
40. 组合总和 II
给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。



示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]


提示:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */
public class Q40 {
    HashMap<Integer, Integer> map = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        //将数组中的数统计进map中,key为数字,value为数字对应的个数
        for (int i = 0; i < candidates.length; i++) {
            if (map.containsKey(candidates[i])) {
                map.replace(candidates[i], map.get(candidates[i]) + 1);
            } else map.put(candidates[i], 1);
        }
        this.dfs(ans, new ArrayList<>(), candidates, 0, target);
        return ans;
    }

    //当前位置position, n表示距离目标还剩下多少,n为0时即为答案
    private void dfs(List<List<Integer>> ans, List<Integer> list, int[] candidates, int position, int n) {
        //递归基
        if (n < 0) return;
        else if (n == 0) {
            ans.add(list);
            return;
        }
        //一般情况,在当前位置position处,有两个选择,要么调用,要么跳过
        //调用:dfs(,,,position+1,n-c[p])
        //跳过:dfs(,,,position+1,n)
        for (int i = position; i < candidates.length; i++) {
            int num = candidates[i];
            int amount = map.get(num);
            if (amount == 1) {
                List<Integer> l = new ArrayList<>(list);
                l.add(num);
                dfs(ans, l, candidates, i + 1, n - num);
            } else if (i > 0 && candidates[i - 1] == num) continue;
            else {//连续字符的首个
                for (int j = 1; j <= amount; j++) {
                    List<Integer> l = new ArrayList<>(list);
                    for (int k = 0; k < j; k++) l.add(num);
                    dfs(ans, l, candidates, i + amount, n - num * j);
                }
            }
        }
    }


    @Test
    public void T40() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        System.out.println(Arrays.toString(candidates));
        System.out.println(target);
        List<List<Integer>> ans = this.combinationSum2(candidates, target);
        System.out.println(ans);
        System.out.println("over");
    }
}
