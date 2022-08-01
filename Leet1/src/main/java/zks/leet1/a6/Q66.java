package zks.leet1.a6;

/*
66. 加一
给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。



示例 1：

输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
示例 2：

输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。
示例 3：

输入：digits = [0]
输出：[1]


提示：

1 <= digits.length <= 100
0 <= digits[i] <= 9
 */
public class Q66 {

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (digits[len - 1] == 9) {//需要进位
            //若全部元素都是9,这时答案数组长度需要加1,先判断这个情况
            boolean allEleAre9 = true;
            for (int i = 0; i < len; i++) {
                if (digits[i] != 9) {
                    allEleAre9 = false;
                    break;
                }
            }
            if (allEleAre9) {
                int[] ans = new int[len + 1];
                ans[0] = 1;
                return ans;
            }
            //排除了特殊情况,ans和digits一样长
            int[] ans = new int[len];
            int i = 0;
            for (; i < len; i++) ans[i] = digits[i];
            ans[--i]++;//i==len-1
            while (ans[i] == 10) {//向前进位,直到不需要进位
                ans[i] = 0;
                i--;
                ans[i]++;
            }
            return ans;
        } else {//不需要进位,复制一份digits,末位加1就行了
            int[] ans = new int[len];
            for (int i = 0; i < len; i++) ans[i] = digits[i];
            ans[len - 1]++;
            return ans;
        }
    }
}
