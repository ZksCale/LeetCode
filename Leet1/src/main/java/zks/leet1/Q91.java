package zks.leet1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
91. 解码方法
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

"AAJF" ，将消息分组为 (1 1 10 6)
"KJF" ，将消息分组为 (11 10 6)
注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。

给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

题目数据保证答案肯定是一个 32 位 的整数。



示例 1：

输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2：

输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
示例 3：

输入：s = "0"
输出：0
解释：没有字符映射到以 0 开头的数字。
含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。


提示：

1 <= s.length <= 100
s 只包含数字，并且可能包含前导零。
 */
public class Q91 {
    public int numDecodings(String s) {
        //用f[i]表示s的前i个字符的解码方式
        int l = s.length();
        int[] f=new int[l+1];
        f[0]=1;
        for(int i=1;i<=l;i++){//保证i>0
            //情况1 单独解码
            if(s.charAt(i-1)!='0') f[i]+=f[i-1];
            //情况2 s(i-2)与s(i-1)合起来是一个小于27的两位数,他俩一起解码
            if(i>1&&s.charAt(i-2)!='0'&&(s.charAt(i-2)-'0')*10+s.charAt(i-1)-'0'<27) {
                f[i]+=f[i-2];
            }
        }

        return f[l];
    }



    @Test
    public void t() {
        System.out.println(numDecodings("123"));
    }
}