package zks.leet1.a2;

/*
 * 10. 正则表达式匹配
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

 
示例 1：

输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
示例 2:

输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3：

输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 

提示：

1 <= s.length <= 20
1 <= p.length <= 30
s 只包含从 a-z 的小写字母。
p 只包含从 a-z 的小写字母，以及字符 . 和 *。
保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class Q10 {
	public static void main(String[] args) {
		Q10 q = new Q10();
		String s = "aaaaaa";
		String p = "aa*ab*";
		System.out.println(q.isMatch(s, p));
	}
	public boolean isMatch(String s, String p) {
		return s.matches(p);
	}
//这代码原理上是错的
//	public boolean isMatch(String s, String p) {
//		// 读p中的字符,读的时候要考虑到它后面是否是'*'
//		// 根据p的读取结果对s进行匹配,匹配成功则读p下一个字符,失败则返回false
//		// 下标
//		int is = 0, ip = 0;
//		while (true) {
//			// 每次循环都要检测,如果两个字符串有一个已经扫描完毕而另一个还有剩余,则说明两个字符串不匹配,此时应该返回false
//			if (is >= s.length() && ip < p.length()) {// 此处,如果ip后面全部都是.*格式的字符,则仍可能是匹配成功的
//				if (hasSuffix(p, ip)) {
//					ip += 2;
//					continue;
//				} else {
//					return false;
//				}
//			} else if (ip >= p.length() && is < s.length()) {
//				return false;
//			} else if (ip >= p.length() && is >= s.length()) {// 同时到达末尾,说明匹配成功
//				return true;
//			}
//			if (hasSuffix(p, ip)) {// *
//				if (p.charAt(ip) == '.') {// .*
//					// ".*"在末尾的效果相当于将is直接指向s.length()
//					is = s.length();
//					ip += 2;
//
//				} else {// a* 匹配零个或多个前面的那一个元素
//					while (s.charAt(is) == p.charAt(ip)) {
//						if (is < s.length() - 1) {
//							is++;
//						} else {
//							is++;
//							break;
//						}
//					}
//					ip += 2;
//				}
//			} else {// 没有*
//				if (p.charAt(ip) == '.') {// .
//					ip++;
//					is++;
//				} else {// a
//					if (p.charAt(ip) != s.charAt(is)) {
//						return false;
//					}
//					ip++;
//					is++;
//				}
//			}
//		}
//
//	}
//
//	// p在下标index处元素是否跟着后缀*
//	private static boolean hasSuffix(String p, int index) {
//		if (index >= p.length() - 1) {
//			return false;
//		}
//		if (p.charAt(index + 1) == '*') {
//			return true;
//		}
//		return false;
//	}
}
