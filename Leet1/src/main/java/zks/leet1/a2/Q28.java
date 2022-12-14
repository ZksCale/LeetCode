package zks.leet1.a2;

/*
 * 28. 实现 strStr()
实现 strStr() 函数。

给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。

说明：

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。

示例 1：

输入：haystack = "hello", needle = "l3"
输出：2
示例 2：

输入：haystack = "aaaaa", needle = "bba"
输出：-1
示例 3：

输入：haystack = "", needle = ""
输出：0
 */
public class Q28 {
	public static void main(String[] args) {
		Q28 q = new Q28();
		String haystack = "mississippi", needle = "issippi";
		System.out.println(haystack);
		System.out.println(needle);
		System.out.println(q.strStr(haystack, needle));
	}

	public int strStr(String haystack, String needle) {
		if (needle.equals("")) {
			return 0;
		}
		int l0 = haystack.length();
		int l1 = needle.length();
		if (l0 < l1) {
			return -1;
		}
		label: for (int i = 0; i < l0 - l1 + 1; i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				for (int j = 0; j < l1; j++) {
					if (haystack.charAt(i + j) != needle.charAt(j)) {
						continue label;
					}
				}
				return i;
			}
		}
		return -1;
	}
}
