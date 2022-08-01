package zks.leet1.a3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
30. 串联所有单词的子串
给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。



示例 1：

输入：s = "barfoothefoobarman", words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
示例 2：

输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
输出：[]
示例 3：

输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
输出：[6,9,12]


提示：

1 <= s.length <= 104
s 由小写英文字母组成
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] 由小写英文字母组成
 */
public class Q30 {

    @Test
    public void T30(){
        String s = "barfoothefoobarman";
        String[] words={"foo","bar"};
        List<Integer> ans = null;
        System.out.println(s);
        System.out.println(words);
        ans = this.findSubstring(s,words);
        System.out.println(ans);
    }

    private HashMap<String, Integer> targetHashMap;
    //用两个哈希map,一个用来保存words和每个word出现的次数,另一个用来做比对,
    // 从字符串的首个字符开始向右扫描,每当读取特定长度的字符后
    //先查询是否已经存在于words表中,不存在可以直接否定当前的字母
    public List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> ans = new ArrayList<>();
        int lengthOfWords = words.length;
        int lengthOfAWord = words[0].length();
        //s不够长说明不存在答案
        if (s.length() < lengthOfWords * lengthOfAWord) return ans;
        //首先存入表中
        this.targetHashMap = new HashMap<>();
        for (String str : words) {
            if (this.targetHashMap.containsKey(str)) {
                this.targetHashMap.replace(str, this.targetHashMap.get(str) + 1);
            } else {
                this.targetHashMap.put(str, 1);
            }
        }
        //辅助哈希映射
        HashMap<String, Integer> auxiliaryMap = new HashMap<>();
        //接着开始比对,扫描从s的首个元素到符合长度的最后一个元素
        label:
        for (int i = 0; i < s.length() + 1 - lengthOfAWord * lengthOfWords; i++) {
            //初始化辅助哈希映射
            auxiliaryMap.clear();
            for (int j = 0; j < lengthOfWords; j++) {
                int offset = i + j * lengthOfAWord;
                String str = s.substring(offset, offset + lengthOfAWord);//查询这个str是否满足条件
                //首先,它必须是包含在目标哈希映射中的
                if (!targetHashMap.containsKey(str)) continue label;
                //其次,它在辅助映射中的value不能大于它在目标映射中的value
                //全都满足上述条件的话,说明这个单词是合格的,将它加入辅助哈希映射中,然后跳向下一个单词
                if (auxiliaryMap.containsKey(str)) {
                    int num0 = targetHashMap.get(str);
                    int num1 = auxiliaryMap.get(str);
                    if (num1 >= num0) continue label;
                    else {
                        auxiliaryMap.replace(str, num1, num1 + 1);
                    }
                } else {
                    auxiliaryMap.put(str, 1);
                }
            }
            //执行到这里时,说明这个位置是符合题意的
            ans.add(i);
        }
        return ans;
    }
}
