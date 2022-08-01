package zks.leet1.a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//17. 电话号码的字母组合
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
//给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//"9键"输入的数字与字母映射关系
//
// 
//
//示例 1：
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//示例 2：
//
//输入：digits = ""
//输出：[]
//示例 3：
//
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
//提示：
//
//0 <= digits.length <= 4
//digits[i] 是范围 ['2', '9'] 的一个数字。
public class Q17 {
	public static void main(String[] args) {
		Q17 q = new Q17();
		String digits = "2349";
		System.out.println(q.letterCombinations(digits));
	}

	private static HashMap<Character, String> hm;

	static {
		hm = new HashMap<Character, String>();
		hm.put('2', "abc");
		hm.put('3', "def");
		hm.put('4', "ghi");
		hm.put('5', "jkl");
		hm.put('6', "mno");
		hm.put('7', "pqrs");
		hm.put('8', "tuv");
		hm.put('9', "wxyz");
	}

	public List<String> letterCombinations(String digits) {
		ArrayList<String> ansr = new ArrayList<>();
		if (digits == null || digits.equals("")) {
			return ansr;
		}
		for(int i=0;i<digits.length();i++) {
			backTrack(ansr, hm.get(digits.charAt(i)));
		}
		return ansr;
	}

	private static void backTrack(ArrayList<String> linklist, String s) {// 链表 "abc"
		int size = linklist.size();
		if (size == 0) {
			for (int i = 0; i < s.length(); i++) {
				linklist.add(s.charAt(i) + "");
			}
		}
		for (int i = 0; i < size; i++) {// 遍历,每取出一个元素,该元素后面加一个新字母并加入到链表尾部,并删除头部的该元素
			String head = linklist.remove(0);
			for (int j = 0; j < s.length(); j++) {
				linklist.add(head + s.charAt(j));
			}
		}
	}

}
