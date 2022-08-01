package zks.leet1.a8;

import org.junit.jupiter.api.Test;

import java.util.*;

/*
89. 格雷编码
n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
第一个整数是 0
一个整数在序列中出现 不超过一次
每对 相邻 整数的二进制表示 恰好一位不同 ，且
第一个 和 最后一个 整数的二进制表示 恰好一位不同
给你一个整数 n ，返回任一有效的 n 位格雷码序列 。



示例 1：

输入：n = 2
输出：[0,1,3,2]
解释：
[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
- 00 和 01 有一位不同
- 01 和 11 有一位不同
- 11 和 10 有一位不同
- 10 和 00 有一位不同
[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
- 00 和 10 有一位不同
- 10 和 11 有一位不同
- 11 和 01 有一位不同
- 01 和 00 有一位不同
示例 2：

输入：n = 1
输出：[0,1]


提示：

1 <= n <= 16
 */
public class Q89 {
    public List<Integer> grayCode(int n) {
        //对于序列Gn,序列Gn+1的组成为:前半段的Gn加上 后半段Gn的倒转再对每个值的首位加1(加上2^n)
        List<Integer> ans = new ArrayList<>();
        if (n == 0) {
            ans.add(0);
            return ans;
        }
        List<Integer> last = grayCode(n - 1);
        ans.addAll(last);
        //对last从后向前遍历,对每个数字都加上2^n再添加进ans中
        int pow = (int) Math.pow(2, n - 1);
        Collections.reverse(last);
        last.replaceAll(integer -> integer + pow);
        ans.addAll(last);
        return ans;
    }

    @Test
    public void t0() {
        System.out.println(this.grayCode(2));
    }
}
