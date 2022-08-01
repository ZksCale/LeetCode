package zks.leet1.a3;


import org.junit.jupiter.api.Test;

/*
32. 最长有效括号
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。



示例 1：

输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"
示例 2：

输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"
示例 3：

输入：s = ""
输出：0


提示：

0 <= s.length <= 3 * 104
s[i] 为 '(' 或 ')'
 */
public class Q32 {
    @Test
    public void T32() {
        String s = "()(()(())";
        System.out.println(this.longestValidParentheses(s));
    }

    //从左向右扫描,可以跳过),对于每一个'('都要求它的有效子串括号长度,当剩下的长度已经小于可能的结果时,可以直接返回
    public int longestValidParentheses( String s) {
        int ans = 0;
        for (int i = 0; i < s.length() - ans; i++) {
            if (s.charAt(i) == ')') continue;
            //对于每一个'(',求有效括号的长度
            // 每当遇到')'时才会产生一对有效的括号
            int left = 1;
            int j = 1;
            int len = 0;
            int offset = 1;
            while (i + j < s.length()) {
                if (s.charAt(i + j) == '(') {
                    left++;
                    offset++;
                } else {//只有当left归0时才能断定这一段括号是有效的
                    left--;
                    offset++;
                    if (left == 0) {
                        len += offset;
                        offset = 0;
                    } else if (left < 0) {
                        break;
                    }
                }
                j++;
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }
}
