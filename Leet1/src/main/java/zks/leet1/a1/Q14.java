package zks.leet1.a1;

/*
 * 14. 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

 

示例 1：

输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：

输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
 

提示：

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] 仅由小写英文字母组成14. 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

 

示例 1：

输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：

输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
 

提示：

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] 仅由小写英文字母组成
 */
public class Q14 {
	public static void main(String[] args) {
		Q14 q = new Q14();
		String[] strs = new String[] { "flower","flow","flight"};
		System.out.println(q.longestCommonPrefix(strs));
	}

	// 从左向右扫描,如果有数组越界或者字符不等,即可退出循环,公共前缀最长也不可能比第一个字符串更长
	public String longestCommonPrefix(String[] strs) {
		StringBuilder ansr = new StringBuilder();
		char c;
		Label: for (int i = 0; i < strs[0].length(); i++) {// 一定存在strs[0]
			c = strs[0].charAt(i);
			for (int j = 0; j < strs.length; j++) {
				if (i >= strs[j].length()) {
					break Label;
				}
				if (strs[j].charAt(i) != c) {
					break Label;
				}
			}
			ansr.append(c);
		}
		return new String(ansr);
	}
}
