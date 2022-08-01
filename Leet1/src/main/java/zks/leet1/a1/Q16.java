package zks.leet1.a2;

import java.util.Arrays;

/*
 * 16. 最接近的三数之和
给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

返回这三个数的和。

假定每组输入只存在恰好一个解。

 

示例 1：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
示例 2：

输入：nums = [0,0,0], target = 1
输出：0
 

提示：

3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-104 <= target <= 104
 */
public class Q16 {
	public static void main(String[] args) {
		Q16 q = new Q16();
		int[] nums = new int[] { -3,-2,-5,3,-4};
		System.out.println(q.threeSumClosest(nums, -1));
	}

	public int threeSumClosest(int[] nums, int target) {
		int ansr = 2147483647/2;
		if (nums != null && nums.length < 3) {
			return 0;
		}
		Arrays.sort(nums);// 排序
		for (int i = 0; i < nums.length - 2; i++) {
			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (Math.abs(ansr - target) > Math.abs(sum - target)) {
					ansr = sum;
				}
				
				if (sum == target) {
					return sum;
				} else if (sum > target) {
					r--;
				} else {
					l++;
				}
			}
		}
		return ansr;
	}
}
