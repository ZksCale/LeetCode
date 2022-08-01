package zks.leet1.a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 15. 三数之和
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：

输入：nums = []
输出：[]
示例 3：

输入：nums = [0]
输出：[]
 

提示：

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */
public class Q15 {
	public static void main(String[] args) {
		Q15 q = new Q15();
		int[] arr = new int[] { -1, 0, 1, 2, -1, -4 };
		System.out.println(Arrays.toString(arr));
		System.out.println(q.threeSum(arr));
	}

	// a+b+c == 0 ----> a+b == -c 两数之和为 -c 就是一组答案
	public List<List<Integer>> threeSum(int[] nums) {
		ArrayList<List<Integer>> ansr = new ArrayList<List<Integer>>();
		if (nums == null || nums.length <= 2) {
			return ansr;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			int l = i + 1;
			int r = nums.length - 1;
			if (nums[i] > 0) {// 不可能有答案了
				break;
			}
			if (i > 0 && nums[i] == nums[i - 1]) {// 去重
				continue;
			}
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (sum == 0) {
					ansr.add(Arrays.asList(nums[i], nums[l], nums[r]));
					while (l < r && nums[l] == nums[++l])
						;
					while (l < r && nums[r] == nums[--r])
						;
				} else if (sum > 0) {
					r--;
				} else if (sum < 0) {
					l++;
				}
			}
		}
		return ansr;
	}
}
