package zks.leet1.a3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
31. 下一个排列
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。

示例 1：

输入：nums = [1,2,3]
输出：[1,3,2]
示例 2：

输入：nums = [3,2,1]
输出：[1,2,3]
示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]


提示：

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */
public class Q31 {
    @Test
    public void T31() {
        int[] nums = new int[]{1, 3,2};
        System.out.println(Arrays.toString(nums));
        this.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        //从右向左扫描,在遇到第一个"顺序对"(如12 56 79)时停下来需要在这个顺序对ab(a<b)处做调整
        //a的右侧一定是降序排列的,a的右侧一定有一个以上的元素比a的数值严格的更大
        //此时要做的修改是:找到a的右侧所有比a大的元素中最小的那个,将它和a换位,然后对a的右侧进行升序排序
        //当整个扫描都没有出现顺序对时,这个排列是字典值最大的那个排列,应该将它翻转
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                //遇到了第一个顺序对,停在这里操作数组后return
                int r = nums.length - 1;
                while (true) {
                    if (nums[r] > nums[i]) break;
                    r--;
                }
                //下面交换,然后排序
                int t = nums[r];
                nums[r] = nums[i];
                nums[i] = t;
                Arrays.sort(nums, i + 1, nums.length);
                return;
            }
        }
        //未遇到任何一个顺序对
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            l++;
            r--;
        }
    }
}
