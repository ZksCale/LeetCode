package zks.leet1.a6;

import org.junit.jupiter.api.Test;

/*
53. 最大子数组和
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分。



示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [5,4,-1,7,8]
输出：23


提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104


进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */
public class Q53 {
    /*
    用f(i)表示"以num[i]为起始元素的子数组的最大和"
    最末尾的元素为起点的值只能等于它本身,也就是说 len = nums.length; 满足:f(len-1)=num[len-1]
    当已知f(i+1)时，求f(i)的递推公式:
    f(i)的值只可能是nums[i]和nums[i]+f(i+1)中的较大者, 这一步也是常数时间内即可求得
    因此,一趟从后向前的线性扫描, 在计算f(i)的同时,用一个变量求得f(i)中最大的那个
    整个算法的时间复杂度为O(n)

     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int ans = nums[len - 1];
        int f_i_1 = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            int f_i = Math.max(nums[i], nums[i] + f_i_1);
            ans = Math.max(f_i, ans);
            f_i_1 = f_i;
        }
        return ans;
    }

    @Test
    public void T53() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(this.maxSubArray(nums));
    }
}
