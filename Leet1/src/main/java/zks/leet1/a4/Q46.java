package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：

输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]
 

提示：

1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q46 {
    /*这个做法是,每轮递归,nums的前一项删除并存入number中,调用nums规模缩减了1的permute(nums),并对于答案中的每一项,都有:将它复制下来,
    假如它的长度为k([2,3,4,5]的长度为4),那么从第一个数的左端开始,到最后一个数的右端,共k+1个空位可以插入数字,对每一项都做复制操作,并在
    它的每一个空位都插入number并加入到答案数组ans中.
    很明显,这个算法存在大量冗余,主要是新建了相当多的中间数组
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i : nums) numbers.add(i);
        return permute(numbers);
    }

    public List<List<Integer>> permute(List<Integer> numbers) {
        List<List<Integer>> ans = new LinkedList<>();
        if (numbers.size() == 1) {
            ans.add(new LinkedList<>(numbers));
            return ans;
        }//递归基
//        List<Integer> numbers0 = new ArrayList<>(numbers);
        Integer number = numbers.remove(0);
        List<List<Integer>> lastAns = permute(numbers);
        for (int i = 0; i <= lastAns.get(0).size(); i++) {
            //首先复制上一轮的答案
            List<List<Integer>> list = new LinkedList<>();
            for (List<Integer> l : lastAns)
                list.add(new LinkedList<>(l));
            //插入number
            for (List<Integer> l : list) {
                l.add(i, number);
                //放入ans中
                ans.add(l);
            }

        }
        return ans;
    }

     */
    /*
    改成回溯解决这个问题,回溯确实可以节省下一定的空间,避免了不少复制操作,这也就剩下了时间
     */
    boolean[] vis;

    public List<List<Integer>> permute(int[] nums) {
        vis = new boolean[nums.length];
        List<List<Integer>> ans = new LinkedList<>();
        backtrack(nums,ans,0,new ArrayList<>());
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            perm.add(nums[i]);
            backtrack(nums, ans, idx + 1, perm);
            perm.remove(idx);
            vis[i] = false;
        }
    }

    @Test
    public void T46() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(nums));
        System.out.println(this.permute(nums));
    }
}
