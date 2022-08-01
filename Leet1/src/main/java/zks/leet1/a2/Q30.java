package zks.leet1.a2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 30. 串联所有单词的子串
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

1 <= s.length <= 10^4
s 由小写英文字母组成
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] 由小写英文字母组成
 */
public class Q30 {
	public static void main(String[] args) {
		String s = "barfoofoobarthefoobarman";
		String[] words = new String[] {"bar","foo","the"};
		System.out.println(new Q30().findSubstring(s, words));
	}
	//用words[]的内容建一个hashmap hm<String ,Integer>String记录了子串,Integer记录了这个子串出现了几次
	//对于s的每一个字符,对以它为首的子串进行判断,从它开始读words[i].length个字符,然后查询这个子字符串是否在hm中,如果不在可以直接否掉这个字符
	//如果存在,需要建立一个hashmap hs<s,i>将这个子串保存在hs中,之后往后走固定长度,并尝试匹配下一个子字符串,如果出现不匹配,或者次数超过了hm中记录的数字,都可以直接否掉
	//如果走到最后也依然匹配,那么可以将答案添加在ArrayList ans中
	//这个遍历直到后续的子字符串长度不够为止
	
	public List<Integer> findSubstring(String s, String[] words) {
		ArrayList<Integer> ans = new ArrayList<>();
		//建表
		HashMap<String, Integer> hm = new HashMap<>();
		//遍历
		return ans;
	}
}
