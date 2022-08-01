package zks.leet1.a0;

import java.util.Arrays;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

算法的时间复杂度应该为 O(log (m+n)) 。

 

示例 1：

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
示例 2：

输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 

 

提示：

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author MyPC
 *
 */
public class Q4 {
	public static void main(String[] args) {
		Q4 q = new Q4();
		int[] nums1 = new int[] {3};
		int[] nums2 = new int[] {2};
		System.out.println(Arrays.toString(nums1));
		System.out.println(Arrays.toString(nums2));
		System.out.println(q.findMedianSortedArrays(nums1, nums2));
	}
	
	//将两个有序数组排序为一个有序数组,其时间复杂度不会低于 O(m+n),而本题要求log(m+n),算法一定是不需要遍历每一个数的
	//寻找两个数组中第k大的元素(本题中k为(m+n)/2),可以比较两个数组中的num[k/2], 两者较小的那个,可以肯定答案不在其中,故而可以丢掉k/2个元素,将问题规模缩减
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {//找中位数
		//保证nums1比nums2短
		if(nums1.length > nums2.length) {
			int[] nums0 = nums1;
			nums1 = nums2;
			nums2 = nums0;
		}
		
		int m = nums1.length;
		int n = nums2.length;
		//求长度为m+n的有序数组的中位数, 设两个数组归并为同一个有序数组后double[] a 中位数为(a[(m+n)/2] + a[(m+n-1)/2])/2
		return (getKthNum(nums1, 0, m, nums2, 0, n, (m+n+1)/2) +
				getKthNum(nums1, 0, m, nums2, 0, n, (m+n+2)/2)) / 2;//统一了奇数与偶数
    }
	//求第k个大的元素, 查找两个数组的第k/2个元素,更小的那段被舍弃,将待查找的数组缩短,并将问题规模缩小,缩小至k==1时,只需要比对两个数组的第一个元素即可找到答案
	//参数说明:nums数组 start起始位置 end终止位置 (左闭右开) k第k个元素 第1个元素即为首个
	public static double getKthNum(int[] nums1,int start1,int end1,int[] nums2,int start2,int end2,int k) {
		//递归基
		if(start1 == end1) {//长度为0
			return nums2[start2 + k - 1];
		}else if(start2 == end2) {
			return nums1[start1 + k -1];
		}
		if(k == 1) {//比对第一个元素,更小的那个是要找的值
			if(nums1[start1] < nums2[start2]) {
				return nums1[start1];
			}else {
				return nums2[start2];
			}
		}
		//nums1长度不足k/2时
		if((end1 - start1) <= k/2) {
			if(nums1[end1-1] <= nums2[k/2 + start2 -1]) {
				return getKthNum(nums1, end1, end1, nums2, start2, end2, k -(end1-start1));
			}else {
				return getKthNum(nums1, start1, end1, nums2, start2 + k/2, end2, k - k/2);
			}
		}else {
			//一般情况
			if(nums1[start1 + k/2 - 1] <= nums2[start2 + k/2 - 1]) {
				return getKthNum(nums1, start1 + k/2, end1, nums2, start2, end2, k - k/2);
			}else {
				return getKthNum(nums1, start1, end1, nums2, start2 + k/2, end2, k - k/2);
			}
		}
	}
}
