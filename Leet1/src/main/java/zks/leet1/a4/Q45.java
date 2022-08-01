package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
45. 跳跃游戏 II
给你一个非负整数数组 nums ，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

假设你总是可以到达数组的最后一个位置。



示例 1:

输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
示例 2:

输入: nums = [2,3,0,1,4]
输出: 2


提示:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
 */
public class Q45 {
    /*    //对数组nums进行处理,将它的值加上它的下标,这样它的值就变成了"最远能够到的索引",然后找到最靠左边的能够到数组最右端的元素的索引
        //O(n^2)并不能让人满意
        public int jump(int[] nums) {
            if (nums.length == 1) return 0;
            for (int i = 0; i < nums.length; i++)
                nums[i] += i;
            return jump(nums, nums.length - 1);
        }

        public int jump(int[] nums, int index) {
            if (nums[0] >= index) return 1;
            for (int i = 0; i < index; i++)
                if (nums[i] >= index) return 1 + jump(nums, i);
            return 0;//
        }*/
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int step = 0;
        int index = 0;
        while (index < nums.length) {
            if (nums[index] + index >= nums.length-1) break;
            int nextIndex = index + 1;
            for (int i = 1; i <= nums[index]; i++)
                if (nums[nextIndex]+nextIndex < nums[index + i]+index+i) nextIndex = index + i;
            step++;
            index = nextIndex;
        }
        return ++step;
    }

    @Test
    public void T45() {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(Arrays.toString(nums));
        System.out.println(this.jump(nums));
    }
}
