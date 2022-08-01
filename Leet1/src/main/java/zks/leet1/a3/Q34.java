package zks.leet1.a4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
34. 在排序数组中查找元素的第一个和最后一个位置
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？


示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]


提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
 */
public class Q34 {
    //使用二分查找
    public int[] searchRange(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);//本题中,二分查找应该自己写
        if (i < 0) return new int[]{-1, -1};
        //i为非负数,说明nums[i]确实等于target,这时i的前后都可能有数个相等的值,向前后分别寻找边界即可得到答案
        int l = i, r = i;
        while (l > 0) {
            if (nums[l] != nums[l-1]) break;
            l--;
        }
        while (r < nums.length - 1) {
            if (nums[r] != nums[r+1]) break;
            r++;
        }
        return new int[]{l,r};

    }

    @Test
    public void T34() {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 6;
        System.out.println(Arrays.toString(this.searchRange(nums,target)));
    }
}

















