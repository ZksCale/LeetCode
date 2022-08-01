package zks.leet1.a9;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
90. 子集 II
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。



示例 1：

输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
public class Q90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int l = nums.length;
        Arrays.sort(nums);
        //子集长度为:[0,l]
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] flags = new boolean[l];
        for (int n = 0; n <= l; n++) {
            bfs(nums, flags, ans, new ArrayList<>(), n, 0);
        }
        return ans;
    }

    private void bfs(int[] nums, boolean[] flags, List<List<Integer>> ans, List<Integer> list, int n, int i) {
        if (list.size() == n) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (; i < nums.length; i++) {
            //去重

            if (i <= 0 || nums[i] != nums[i - 1] || flags[i - 1]) {
                //添加
                list.add(nums[i]);
                flags[i] = true;
                bfs(nums, flags, ans, list, n, i + 1);
                list.remove(list.size() - 1);
                flags[i] = false;
            }
        }
    }

    @Test
    public void t() {
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> lists = this.subsetsWithDup(nums);
        lists.forEach(System.out::println);
    }
}
