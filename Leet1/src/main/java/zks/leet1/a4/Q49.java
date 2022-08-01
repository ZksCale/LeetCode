package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
49. 字母异位词分组
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。



示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:

输入: strs = [""]
输出: [[""]]
示例 3:

输入: strs = ["a"]
输出: [["a"]]


提示：

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母
 */
public class Q49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        //字典,key是排序好的字母列表
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            char[] chars = new char[s.length()];
            s.getChars(0, s.length(), chars, 0);
            Arrays.sort(chars);
            String key=new String(chars);
            if (hashMap.containsKey(key))//若已经存在key
                hashMap.get(key).add(s);
            else {//若不存在key
                ArrayList<String> strings = new ArrayList<>();
                strings.add(s);
                hashMap.put(key, strings);//新增
            }
        }
        List<List<String>> ans = new ArrayList<>();
        ans.addAll(hashMap.values());
        return ans;
    }

    @Test
    public void T49() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(this.groupAnagrams(strs));
    }
}
