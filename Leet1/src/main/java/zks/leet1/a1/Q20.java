package zks.leet1.a1;

import java.util.LinkedList;

/*
 * 20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
 

示例 1：

输入：s = "()"
输出：true
示例 2：

输入：s = "()[]{}"
输出：true
示例 3：

输入：s = "(]"
输出：false
示例 4：

输入：s = "([)]"
输出：false
示例 5：

输入：s = "{[]}"
输出：true
 

提示：

1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
 */
public class Q20 {
	public static void main(String[] args) {
		String s = "({[]})";
		Q20 q = new Q20();
		System.out.println(s);
		System.out.println(q.isValid(s));
	}

	// 本题中,使用一个栈结构,遇到左括号就入栈,遇到右括号出栈,出栈的括号如果不能匹配就返回false,如果可以匹配就继续读取
	// 输入字符串扫描一遍后,如果栈也是空的,则可返回true
	public boolean isValid(String s) {
		// 用LinkedList作为栈,只在头部做add remove操作
		LinkedList<Character> l = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {// {[(进栈 )]}出栈
			char c = s.charAt(i);
			if (c == '{' || c == '[' || c == '(') {
				l.addFirst(c);
			}else if(c=='}') {
				if(l.size() == 0 || l.removeFirst()!='{') {
					return false;
				}
			}else if(c==']') {
				if(l.size() == 0 || l.removeFirst()!='[') {
					return false;
				}
			}else if(c==')') {
				if(l.size() == 0 || l.removeFirst()!='(') {
					return false;
				}
			}
		}
		if (l.size() > 0) {
			return false;
		}
		return true;
	}
}
