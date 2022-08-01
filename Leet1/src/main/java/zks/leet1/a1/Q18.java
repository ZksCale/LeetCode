package zks.leet1.a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 18. 四数之和
给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] 
（若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d < n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。

 

示例 1：

输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
示例 2：

输入：nums = [2,2,2,2,2], target = 8
输出：[[2,2,2,2]]
 

提示：

1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
 */
public class Q18 {
	public static void main(String[] args) {
		Q18 q = new Q18();
		int[] nums = new int[] { 2, 2, 2, 2, 2 };
		int target = 8;
		System.out.println(Arrays.toString(nums));
		System.out.println("target = " + target);
		System.out.println(q.fourSum(nums, target));
	}

	// b+c+d==target-a a从做向右做扫描, 对于任何一个a, 考虑在它右边的元素组成的子序列中找到bcd使得和为t = target-a
	// 将nums排序, 升序排列
	// 在子序列中,b从左向右扫描, cd作为左右指针从b的右侧子序列的左右两端出发 sum=b+c+d 根据sum和
	// t之间的大小关系决定cd如何移动,当cd相遇后,移动b
	// 剔除重复解:当找到b+c+d=t时, cd向中间移动一步,如果移动后指向的数字与原来的值相等,这时就是碰到了重复解
	public List<List<Integer>> fourSum(int[] nums, int target) {
		ArrayList<List<Integer>> ansr = new ArrayList<>();
		if (nums.length < 4) {// 特殊情况
			return ansr;
		}
		Arrays.sort(nums);
		for (int a = 0; a < nums.length - 3; a++) {
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			for (int b = a + 1; b < nums.length - 2; b++) {
				if (b > a + 1 && nums[b] == nums[b - 1]) {
					continue;
				}
				int c = b + 1;
				int d = nums.length - 1;
				while (c < d) {
					int sum = nums[a] + nums[b] + nums[c] + nums[d];
					if (sum == target) {// 添加答案 剔除重复解
						ansr.add(new ArrayList<Integer>(Arrays.asList(nums[a], nums[b], nums[c], nums[d])));
						c++;
						d--;
						while (c < d && nums[c] == nums[c - 1]) {
							c++;
						}
						while (c < d && nums[d] == nums[d + 1]) {
							d--;
						}
					} else if (sum < target) {
						c++;
					} else if (sum > target) {
						d--;
					}
				}
			}
		}
		return ansr;
	}
}
