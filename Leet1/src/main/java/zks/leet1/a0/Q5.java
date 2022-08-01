package zks.leet1.a1;

import java.util.HashSet;

/*
 * 给你一个字符串 s，找到 s 中最长的回文子串。
示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：
输入：s = "cbbd"
输出："bb"
提示：
1 <= s.length <= 1000
s 仅由数字和英文字母组成
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q5 {
	public static void main(String[] args) {
		Q5 q = new Q5();
		String s = "cbbd";
		System.out.println(s);
		System.out.println(q.longestPalindrome(s));
	}
	private static HashSet<String> palindromeSet = new HashSet<>();

	public String longestPalindrome(String s) {
		String rslt = "";
		for(int i = 1;i <= s.length();i++) {//子串长度
			for(int j = 0;j + i <= s.length();j++) {//遍历每个子串
				String ts = s.substring(j, j+i);
				if(isPalindromed(ts)){
					if(rslt.length() < ts.length()) {
						rslt = ts;
					}
				}
			}
		}
		return rslt;
	}
	/*优化1: 对于一个回文数, 向它的左右两端加上相同的字符, 它依旧是一个回文字符串
	 * 考虑到这一点, 使用动态规划, 用哈希表存放已有的回文字符串,在判断新串是否是回文字符串时,先判断它两端的字符是否相等,然后再判断中间的子串是否在表中,如果存在,
	 * 那么它是回文串, 这样自上而下地找完全部子串. 优化后, 判断变为常数次, 遍历子串依旧是O(n^2),故而整体时间复杂度是 O(n^2)
	 * 
	 * 另一种解法:回文子串,从后往前读依旧是它本身,所以找最大的回文子串,它只能存在于这个字符串和它倒叙的公共子串中, 然后再比对下标判断是否是回文数
	 * 时间复杂度 O(n^2)
	 * 
	 */
	public static boolean isPalindromed(String s) {
		if(s.length() == 1) {
			palindromeSet.add(s);
			return true;
		}
		if(s.length() == 2) {
			if(s.charAt(0) == s.charAt(1)) {
				palindromeSet.add(s);
				return true;
			}else {
				return false;
			}
		}
		if(palindromeSet.contains(s.substring(1, s.length()-1))) {
			if(s.charAt(0) == s.charAt(s.length()-1)) {
				palindromeSet.add(s);
				return true;
			}
		}
		return false;
		
	}
	/*
	 * 解法1: 从大到小遍历s的子串, 长度从s.length到2, 找到的第一个回文子串就是答案,
	 * 用一个函数判断传入的字符串是否是回文的(左右指针从头和尾向中间移动,判断是否相等) 复杂度:s长度是n的话 最差情况需要遍历所有子串1次,复杂度O(n^3)
	 * 时间效率上是不能接受的
	 */
//	public String longestPalindrome(String s) {
//		
//		for(int i = s.length();i >= 1;i--) {
//			for(int j = 0;j + i <= s.length();j++){
//				if(isPalindromed(s.substring(j, j+i))) {
//					return s.substring(j, j+i);
//				}
//			}
//		}
//		return null;
//    }
//	public static boolean isPalindromed(String s) {
//		if(s == null) {
//			return false;
//		}
//		if(s.length() == 1) {
//			return true;
//		}
//		for(int left=0,right=s.length()-1;left<right;left++,right--) {
//			if(s.charAt(left) != s.charAt(right)) {
//				return false;
//			}
//		}
//		return true;
//	}
}
