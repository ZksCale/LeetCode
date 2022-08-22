/*
package zks.leet1.a7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

*/
/*
78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。



示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同
 *//*

public class Q78 {
    //试图用更精简的代码实现:
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;
        for (int n = 0; n <= length; n++) {
            subsets(n, 0, nums, new ArrayList<>(), ans);
        }
        return ans;
    }

    private  void subsets(int n, int i, int[] nums, List<Integer> list, List<List<Integer>> ans) {
        if(list.size()==n){
            ans.add(new ArrayList<>(list));
            return;
        }
        for(;i<nums.length;i++){
            list.add(nums[i]);
            subsets(n, i+1, nums, list, ans);
            list.remove(list.size()-1);
        }
    }

    //可以这么想, 子集中的元素个数为:0到length个
    public List<List<Integer>> subsets0(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;
        for (int n = 0; n <= nums.length; n++) {
            subsets0(n, 0, nums, new ArrayList<>(), ans);
        }
        return ans;
    }

    private void subsets0(int n, int i, int[] nums, List<Integer> list, List<List<Integer>> ans) {
        if (n == 0) {
            ans.add(new ArrayList<>());
            return;
        }
        if (n == nums.length) {
            ans.add(Arrays.stream(nums).boxed().toList());
            return;
        }
        if (n == list.size()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        //递归
        for (; i < nums.length; i++) {
            list.add(nums[i]);
            subsets0(n, i+1, nums, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
*/
