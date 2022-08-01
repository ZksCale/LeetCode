package zks.leet1.a7;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。



注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。


示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"
示例 3:

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。


提示：

1 <= s.length, t.length <= 105
s 和 t 由英文字母组成


进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class Q76 {
    public String minWindow(String s, String t) {
        //本题还是去看题解了, 本身想法有偏差,本打算用两个hash表比对, 确实存在问题
        //题解:滑动窗口 lr两个指针, 判断两个指针之间的区间[l,r)是否包含t的全部字符,若包含,试图将l右移,若不包含,将r右移
        //用变量ans记录下最短的子串,遍历结束后就是题解
        int l = 0, r = 0;
        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            Character k = t.charAt(i);
            if (tMap.containsKey(k)) tMap.put(k, tMap.get(k) + 1);
            else tMap.put(k, 1);
        }
        HashMap<Character, Integer> sMap = new HashMap<Character, Integer>();
        //r-l是子串的长度,如果子串的长度比t的长度更小,不需要考虑比对,一定不是子串
        String ans = s + " ";
        while (r <= s.length()) {
            if (r - l >= t.length() && this.containsSubString(sMap, tMap)) {
                if (r - l < ans.length()) ans = s.substring(l, r);
                this.removeCharFromMap(sMap, s.charAt(l));
                l++;
            } else if (r < s.length()) {
                this.addCharToMap(sMap, s.charAt(r));
                r++;
            } else r++;
        }
        if (ans.length() > s.length()) return "";
        else return ans;
    }

    //比对两个hashmap判断s中是否包含了t中的全部字符
    private boolean containsSubString(HashMap<Character, Integer> sMap, HashMap<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (!sMap.containsKey(key) || sMap.get(key) < value) return false;
        }
        return true;
    }

    private void addCharToMap(HashMap<Character, Integer> map, Character c) {
        if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
        } else {
            map.put(c, 1);
        }
    }

    private void removeCharFromMap(HashMap<Character, Integer> map, Character c) {
        if (map.containsKey(c)) {
            Integer v = map.get(c);
            if (v == 1) map.remove(c);
            else map.put(c, v - 1);
        }
    }

    @Test
    public void T76() {
        String s="ADOBECODEBANC";
        String t="ABC";
        System.out.println(this.minWindow(s, t));
    }
}
