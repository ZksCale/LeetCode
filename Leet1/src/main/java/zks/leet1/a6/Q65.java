package zks.leet1.a6;

import org.junit.jupiter.api.Test;

/*
65. 有效数字
有效数字（按顺序）可以分成以下几个部分：

一个 小数 或者 整数
（可选）一个 'e' 或 'E' ，后面跟着一个 整数
小数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
下述格式之一：
至少一位数字，后面跟着一个点 '.'
至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
一个点 '.' ，后面跟着至少一位数字
整数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
至少一位数字
部分有效数字列举如下：["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]

部分无效数字列举如下：["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]

给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。



示例 1：

输入：s = "0"
输出：true
示例 2：

输入：s = "e"
输出：false
示例 3：

输入：s = "."
输出：false


提示：

1 <= s.length <= 20
s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 */
public class Q65 {

    public boolean isNumber(String s) {
        int len = s.length();
        if (len == 0) return false;
        //如果存在e,那么就将它分割,后半部分一定是整数
        int e = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                e = i;
                break;
            }
        }
        if (e < 0) return isInteger(s) || isDecimal(s);//不包含e的情况
        else {//存在e, [0,e) (e,len)两个部分,前一个部分是小数或整数, 后一个部分必须是整数
            String front = s.substring(0, e);
            String rear = s.substring(e + 1);
            return (isInteger(front) || isDecimal(front)) && isInteger(rear);
        }
    }

    //判断给定的字符串是否是一个整数
    //'0'==48;'9'==57
    private boolean isInteger(String s) {
        //可选的符号
        int len = s.length();
        if (len == 0) return false;
        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
        //i位必须是一个数字
        if (i == s.length() || notANum(s.charAt(i++))) return false;
        for (; i < len; i++) {
            if (notANum(s.charAt(i))) return false;
        }
        return true;
    }

    //判断给定的字符串是否是一个小数
    private boolean isDecimal(String s) {
        //处理s不够长的情况
        int len = s.length();
        if (len == 0) return false;
        int i = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') i++;
        if (len - i < 2) return false;//去掉符号后,必须剩下至少两个字符
        //首先,要有小数点'.',不包含小数点就一定不是小数
        int p = -1;
        for (int j = i; j < len; j++) {
            if (s.charAt(j) == '.') {//p是最靠左的'.'
                p = j;
                break;
            }
        }
        if (p == -1) return false;//不包含'.'的情况
        //小数被分割成了两个部分[i,p) (p,len) 由于长度被限定为2以上,不会出现左右两个部分都为空的情况
        //左半部分
        for (; i < p; i++) if (notANum(s.charAt(i))) return false;
        //运行结束后,i=p
        //右半部分
        for (i++; i < len; i++) if (notANum(s.charAt(i))) return false;
        return true;
    }

    private boolean notANum(char c) {
        return c < '0' || '9' < c;
    }

    @Test
    public void T65() {
        String s = "-4.13e645";
        System.out.println(isNumber(s));
    }
}
