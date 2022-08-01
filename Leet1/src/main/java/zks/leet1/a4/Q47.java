package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
47. 全排列 II
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。



示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


提示：

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
public class Q47 {
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        List<List<Integer>> ans = new LinkedList<>();
        backtrack(nums, ans, 0, new ArrayList<>());//递归函数执行完毕后的结果,参数中的ans会被填入所有的全排列
        return ans;
    }

    //这个递归函数最终执行完毕后,应该是将nums中的元素进行全排列,并且没有重复地添加进ans中
    //注意这里idx指的是perm中的第几个元素
    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        //递归基
        if (idx == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        //一般情况,for循环,
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])) continue;
            //根据visited找到
            perm.add(nums[i]);
            visited[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            visited[i] = false;
            perm.remove(idx);
        }
    }


    @Test
    public void T47() {
        int[] nums = {1, 0, 2};
        System.out.println(this.permuteUnique(nums));
    }
}
