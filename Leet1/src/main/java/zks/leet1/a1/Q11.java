package zks.leet1.a1;

import java.util.Arrays;

/*
 * 11. 盛最多水的容器
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。

 

示例 1：



输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：

输入：height = [1,1]
输出：1
 

提示：

n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
public class Q11 {
	public static void main(String[] args) {
		Q11 q = new Q11();
		int[] arr = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(Arrays.toString(arr));
		System.out.println(q.maxArea(arr));
	}

	// 暴力解法, 遍历每一个矩形,并找出其中面积最大的那个
	// 对暴力解法进行优化, 考虑一个矩形, 它有长边,短边和底边, 它的面积是短边*底边,如果移动长边,面积不可能增加
	// 左右两端同时出发,每次向中间移动端的那个边,当两个指针重合时,即为完成遍历, O(n)
	public int maxArea(int[] height) {
		int lp = 0;
		int rp = height.length - 1;
		int maxarea = 0;
		while(lp < rp) {
			//
			int a = Math.min(height[lp], height[rp]) * (rp - lp);
			maxarea = Math.max(maxarea, a);
			if(height[lp] >= height[rp]) {
				rp--;
			}else {
				lp++;
			}
		}

		return maxarea;
	}
}
