package zks.leet1;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/*
87. 扰乱字符串
使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
如果字符串的长度为 1 ，算法停止
如果字符串的长度 > 1 ，执行下述步骤：
在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。



示例 1：

输入：s1 = "great", s2 = "rgeat"
输出：true
解释：s1 上可能发生的一种情形是：
"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
算法终止，结果字符串和 s2 相同，都是 "rgeat"
这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
示例 2：

输入：s1 = "abcde", s2 = "caebd"
输出：false
示例 3：

输入：s1 = "a", s2 = "a"
输出：true


提示：

s1.length == s2.length
1 <= s1.length <= 30
s1 和 s2 由小写英文字母组成
通过次数49,113提交次数102,516
 */
public class Q87 {
    public boolean isScramble0(String s1, String s2) {

        int l = s1.length();
        if (l <= 3) {
            return hasSameChars(s1, s2);
        }
        if (!hasSameChars(s1, s2)) return false;
        //若头部或尾部有相同的字符,可以将其去掉,不影响结果
        char head1 = s1.charAt(0), head2 = s2.charAt(0);
        char tail1 = s1.charAt(l - 1), tail2 = s2.charAt(l - 1);
        if (head1 == head2 && head1 != tail2) return isScramble(s1.substring(1), s2.substring(1));
        if (head1 == tail2 && head1 != head2) return isScramble(s1.substring(1), s2.substring(0, l - 1));
        if (tail1 == head2 && tail1 != tail2) return isScramble(s1.substring(0, l - 1), s2.substring(1));
        if (tail1 == tail2 && tail1 != head2) return isScramble(s1.substring(0, l - 1), s2.substring(0, l - 1));

        boolean ans = false;
        for (int c = 1; c < l - 1; c++) {
            String s1l = s1.substring(0, c);//与s2前c个 后c个比对
            String s1r = s1.substring(c);//与s2后l-c个 前l-c个比对
            String c1 = s2.substring(0, c), c2 = s2.substring(l - c);
            String l_c1 = s2.substring(0, l - c), l_c2 = s2.substring(c);
            ans = ans || (isScramble(s1l, c1) && isScramble(s1r, l_c2)) || (isScramble(s1l, c2) && isScramble(s1r, l_c1));
        }
        return ans;
    }


    //比对两个字符串是否含有相同种类与个数的字符
    private boolean hasSameChars(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return false;
        if (s1.length() != s2.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            if (map.containsKey(c)) map.put(c, map.get(c) + 1);
            else map.put(c, 1);
        }
        for (int i = 0; i < s1.length(); i++) {
            Character c = s2.charAt(i);
            if (!map.containsKey(c)) return false;
            else if (map.get(c) > 1) map.put(c, map.get(c) - 1);
            else map.remove(c);
        }
        return map.isEmpty();
    }

    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return false;
        if (s1.length() != s2.length()) return false;
        int l = s1.length();
        int[][][] dp = new int[l+1][l+1][l+1];//初始为0 代表未填充, 为1代表匹配, -1代表不匹配 dp[i][j][k]
        boolean ans=false;

        return isScramble(s1,s2,0,0,l,dp);
    }

    private boolean isScramble(String s1, String s2, int i, int j, int k, int[][][] dp) {
        if (dp[i][j][k] != 0) return dp[i][j][k] > 0;
        String sub1 = s1.substring(i, i + k);
        String sub2 = s2.substring(j, j + k);
        if (!hasSameChars(sub1, sub2)) {
            dp[i][j][k] = -1;
            return false;
        }
        if (sub1.equals(sub2)) {
            dp[i][j][k] = 1;
            return true;
        }
        boolean ans = false;
        for(int c=0;c<k-1;c++){
            ans=ans||(isScramble(s1, s2, i, j, c, dp) && isScramble(s1, s2, i+c, j+c, k-c, dp))
                    || (isScramble(s1, s2, i, j+k-c, c, dp) && isScramble(s1, s2, i+c, j, k-c, dp));
        }
        if (ans) dp[i][j][k] = 1;
        else dp[i][j][k] = -1;
        return ans;
    }

    @Test
    public void t87() {
        String s1 = "hobobyrqd",
                s2 = "hbyorqdbo";
        boolean scramble = this.isScramble(s1, s2);
        System.out.println(scramble);
//        System.out.println(this.hasSameChars(s1, s2));
    }
}
