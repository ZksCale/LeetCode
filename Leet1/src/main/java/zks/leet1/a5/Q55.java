package zks.leet1.a6;

/*
55. 跳跃游戏
给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。



示例 1：

输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
示例 2：

输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。


提示：

1 <= nums.length <= 3 * 104
0 <= nums[i] <= 105
 */
public class Q55 {
    /*
    和之前的跳格子一样,每一步都贪心地向前跳跃,挑选能够到的格子中价值最高的那个,出现价值同样高的格子时,挑选更靠右的
    当前的格子内的值加上它的秩如果超过了nums的长度,说明跳到了最后,返回true
    如果某个序列无法跳到最后一个格子,一定是停在了某个值为0的位置
     */
    public boolean canJump(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            if (nums[index] + index >= nums.length - 1) return true;
            if (nums[index] == 0) return false;
            int i_max = index + 1;
            for (int i = 2; i <= nums[index]; i++) {
                if (i_max + nums[i_max] <= index + i + nums[index + i]) i_max = index + i;
            }
            index = i_max;
        }
        return false;
    }

}
