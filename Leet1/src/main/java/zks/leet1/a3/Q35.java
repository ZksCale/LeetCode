package zks.leet1.a4;

import org.junit.jupiter.api.Test;

/*
35. 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。



示例 1:

输入: nums = [1,3,5,6], target = 5
输出: 2
示例 2:

输入: nums = [1,3,5,6], target = 2
输出: 1
示例 3:

输入: nums = [1,3,5,6], target = 7
输出: 4


提示:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 为 无重复元素 的 升序 排列数组
-104 <= target <= 104
 */
public class Q35 {
    //二分查找
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mi = (lo + hi) >> 1;
            if (target < nums[mi]) hi = mi;
            else lo = mi + 1;
        }
        lo--;//lo是不大于target的最大秩
        if (lo == -1) return 0;
        return (nums[lo] == target) ? lo : hi;
    }

    @Test
    public void T35() {
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        System.out.println(this.searchInsert(nums, target));
    }
}
