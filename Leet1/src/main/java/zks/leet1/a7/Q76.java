package Q70;

import java.util.HashMap;

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
    //动态维护一个滑动窗口,指针l和r指向这个窗口的头部和尾部,同时用一个哈希表维护窗口中包含的字母以及对应字母的个数
    //要注意的是,只需要关心t中出现的集合,s中任何的t中未曾出现的字母都是无关紧要的,所以考虑对s进行预处理
    //对于s中的每一个字母,如果它不存在与t中,将它置为' ',在窗口滑动时遇到空格直接跳过
    //其次,由于是为了找最小的,在已经找到了一个答案时,如果l和r夹着的区域大于当前找到的最小答案,不用对它进行判断,因为就算它包含了t中元素,它也不是最小解
    public String minWindow(String s, String t) {
        if (t.length() == 0) return "";
        if (s.length() < t.length()) return "";
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else tMap.put(c, 1);
        }//tMap中保存了字母以及对应的个数
        //下面对s进行预处理
        StringBuilder s0 = new StringBuilder(s);
        for (int i = 0; i < s0.length(); i++) if (!tMap.containsKey(s0.charAt(i))) s0.setCharAt(i, ' ');
        //下面使用滑动窗口遍历
        int l = 0, r = 1;//窗口约定为区间[l.r)
        HashMap<Character, Integer> sMap = new HashMap<>();
        return "";
    }


}
