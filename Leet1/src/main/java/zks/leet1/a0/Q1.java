package zks.leet1.a1;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
 

提示：

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案
进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？


 */
public class Q1 {
	public static void main(String[] args) {
		Q1 q = new Q1();
		System.out.println(Arrays.toString(q.twoSum(new int[] {2,7,11,15},9)));
		System.out.println(Arrays.toString(q.twoSum(new int[] {3,2,4},6)));
		System.out.println(Arrays.toString(q.twoSum(new int[] {3,3},6)));
	}
	public int[] twoSum(int[] nums, int target) {
		//用哈希表处理, key为target-nums[i], value为下标i
		HashMap<Integer, Integer> hmap = new HashMap<>();
		for(int i = 0;i < nums.length;i++){
			if(hmap.containsKey(nums[i])) {
				return new int[] {hmap.get(nums[i]),i};
			}
			hmap.put(target-nums[i], i);
		}
//		for(int i = 0;i < nums.length-1;i++) {
//			for(int j = i+1;j < nums.length;j++) {
//				if(nums[j] == target - nums[i]) {
//					return new int[] {i,j};
//				}
//			}
//		}
		 return null;
	}
}
