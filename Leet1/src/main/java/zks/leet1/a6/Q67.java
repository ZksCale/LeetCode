package zks.leet1.a7;

import org.junit.jupiter.api.Test;

/*
67. 二进制求和
给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。



示例 1:

输入: a = "11", b = "1"
输出: "100"
示例 2:

输入: a = "1010", b = "1011"
输出: "10101"


提示：

每个字符串仅由字符 '0' 或 '1' 组成。
1 <= a.length, b.length <= 10^4
字符串如果不是 "0" ，就都不含前导零。
 */
public class Q67 {

    public String addBinary(String a, String b) {
        //处理两个加数,为较短的那个前面加上数个"0",使它们两个长度一致
        if (a.length() > b.length()) {//保证a.len<=b.len
            String t = a;
            a = b;
            b = t;
        }
        a = "0".repeat(b.length() - a.length()) + a;
        //现在它们一样长了
        StringBuilder ans = new StringBuilder();
        char C = '0';//进位
        for (int i = a.length() - 1; i >= 0; i--) {
            char A = a.charAt(i), B = b.charAt(i);
            ans.append(xor(xor(A, B), C));//当前位等于A^B^C
            //C=(A&B)|((A^B)&C)
            C = or(and(A, B), and(xor(A, B), C));
        }
        if (C == '1') ans.append(C);
        ans.reverse();
        return new String(ans);
    }

    private char and(char a, char b) {
        if (a == '1' && b == '1') return '1';
        return '0';
    }

    private char or(char a, char b) {
        if (a == '1' || b == '1') return '1';
        return '0';
    }

    private char xor(char a, char b) {
        if (a == b) return '0';
        return '1';
    }

    @Test
    public void T67() {
        String a = "11";
        String b = "1";
        System.out.println(this.addBinary(a, b));
    }
}
