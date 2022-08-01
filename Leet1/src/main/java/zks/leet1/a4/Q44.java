package zks.leet1.a5;

import org.junit.jupiter.api.Test;

/*
44. 通配符匹配
给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输出: false
 */
public class Q44 {
    /*
    由于有'*'的存在,匹配算法中只考虑挨个字符遍历p,在遇到'*'时是没法断言*匹配多少个字符的
    考虑对问题进行简化,从p的两端出发,只处理a~z以及?,如果遇到不匹配即可返回false,遇到*停止
    处理完成后,会有三种情况:p为空串,p为"*",p为"*...*"
    前两种都是平凡情况,很容易做出判断
    对于第三种来说,不难想到,s和p匹配的充要条件是:两个星号中间夹着的子串在s中做一次搜索,搜索的结果就是返回的结果
    因此,可以脱掉两端的*,对s使用某种搜索,这里先不考虑优化这个检索的办法,暴力地从s的第一个字符出发,只要两者首个字符相同,就递归地尝试匹配
     */
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, s.length(), p, 0, p.length());
    }

    //两个字符串左右两端的端点,左闭右开[ls,rs) [lp,rp)
    public boolean isMatch(String s, int ls, int rs, String p, int lp, int rp) {

        //裁剪左端
        while (lp < rp) {
            if (p.charAt(lp) == '*') break;
            if (rs - ls <= 0) return false;
            //s和p在lp和ls处都是非空的,可以尝试匹配,成功就裁剪掉,失败就return
            if (p.charAt(lp) == '?' || p.charAt(lp) == s.charAt(ls)) {
                lp++;
                ls++;
            } else return false;
        }
        //裁剪右端
        while (lp < rp) {
            if (p.charAt(rp - 1) == '*') break;
            if (rs - ls <= 0) return false;
            if (p.charAt(rp - 1) == '?' || p.charAt(rp - 1) == s.charAt(rs - 1)) {
                rp--;
                rs--;
            } else return false;
        }
        if (lp >= rp) {//p==""
            if (ls >= rs) return true;
            else return false;
        }
        if (ls >= rs) {//s==""
            for(int i=0;i<rp-lp;i++){
                if(p.charAt(i+lp)!='*') return false;
            }
            return true;
        }

        if (p.substring(lp, rp).equals("*")) return true;//"*"
        if (rs - ls <= 0) return isMatch(s, 0, 0, p, lp, rp);
        if (rp - lp < 2) return isMatch(s, ls, rs, p, lp, rp);//平凡情况
        if (rp - lp == 2) return true;
        else {//*开头*结尾的一个匹配字符串,要做的是将两端的*脱掉,然后在s中进行搜索
            boolean ans = false;
            lp++;
            rp--;
            if (rs <= ls) ans |= isMatch(s, ls, rs, p, lp, rp);
            for (int len = 1; len <= rs - ls; len++) {
                for (int i = 0; len + i + ls <= rs; i++) {
                    ans |= isMatch(s, ls + i, ls + i + len, p, lp, rp);
                    if (ans) return true;
                }
            }
        }
        return false;
    }


    @Test
    public void T44() {
        String s = "aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba";
        String p = "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*";
        System.out.println(s);
        System.out.println(p);
        System.out.println(this.isMatch(s, p));
    }
}
