package zks.leet1.a4;

import org.junit.jupiter.api.Test;

/*
33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。



示例 1：

输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例 2：

输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：

输入：nums = [1], target = 0
输出：-1


提示：

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
nums 中的每个值都 独一无二
题目数据保证 nums 在预先未知的某个下标上进行了旋转
-10^4 <= target <= 10^4


进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 */
public class Q33 {
    //无脑的做法是:和nums[0]比较大小,可能命中、大于、小于三种情况,命中直接返回,target更大说明待查找元素在[0,k)
    //target更小说明元素在[k,length)内,然后依据范围遍历查找,在最坏的情况下,时间效率是O(n)
    public int search(int[] nums, int target) {
        if (target < nums[0]) {//后向前
            for (int i = nums.length - 1; i > 0; i--) {
                if (target == nums[i]) return i;
                if (nums[i] < target) return -1;
            }
        } else if (nums[0] == target) {
            return 0;
        } else {//前向后
            for (int i = 0; i < nums.length; i++) {
                if (target == nums[i]) return i;
                if (target < nums[i]) return -1;
            }
        }
        return -1;
    }

    @Test
    public void T33() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 7;
        System.out.println(this.search(nums, target));
    }
}
