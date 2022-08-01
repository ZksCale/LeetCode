package zks.leet1.a1;

import java.util.HashSet;

/*
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
示例 1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q3 {
	public static void main(String[] args) {
		String s0 = "abcdadc";
		String s1 = "bbbbbbbbbbbbbbb";
		String s2 = "au";
		Q3 q = new Q3();
		System.out.println(q.lengthOfLongestSubstring(s0));
		System.out.println(q.lengthOfLongestSubstring(s1));
		System.out.println(q.lengthOfLongestSubstring(s2));

	}
//这种写法的时间复杂度是On2,不能接受
//	public int lengthOfLongestSubstring(String s) {
//		if (s.equals("")) {
//			return 0;
//		}
//		
//		// 现在,答案一定小于等于将s中字符存入哈希表中的size(), 一定大于等于1
//		HashSet<Character> hs = new HashSet<>();
//		for (int i = 0; i < s.length(); i++) {
//			hs.add(s.charAt(i));
//		}
//		if (hs.size() == 1) {// 特殊情况
//			return 1;
//		}
//		for (int l = hs.size(); l > 1; l--) {
//			for (int i = 0; i + l <= s.length(); i++) {
//				if (isUnified(s.substring(i, i + l))) {
//					return l;
//				}
//			}
//		}
//		return 1;
//	}
//	public static boolean isUnified(String s) {// 判断给定的String是否是不含重复字符的
//		// 使用HashSet
//		HashSet<Character> hs = new HashSet<>();
//		for (int i = 0; i < s.length(); i++) {
//			hs.add(s.charAt(i));
//		}
//		return (s.length() == hs.size());
//	}
	//考虑使用左右指针,左右指针中间夹着的那段子串应该时刻保持为不重复字符的子串使用一个变量记录这些子串中最大的长度, 左右指针只会往右移动,所以时间复杂度是O(2n)
	public int lengthOfLongestSubstring(String s) {
		HashSet<Character> hs = new HashSet<>();
		//右指针右移并添加字符进入hs中,若该add方法返回false,说明左指针指向的元素作为开头的最长不重复子串已经找到了, 将左指针向右移动一位,并删除hs中左指针指着的元素
		int l = 0;
		int r = 0;
		int ans = 0;
		//应保证右指针不出现在左指针的左边, 右指针抵达最后一个元素后结束循环
		while(r < s.length()) {
			if(hs.contains(s.charAt(r))){//r指向的元素是待添加的,r右移的同时添加
				//重复,左指针右移
				hs.remove(s.charAt(l++));
			}else {
				//不重复,右指针右移
				hs.add(s.charAt(r++));
				ans = Math.max(ans, r-l);
			}
		}
		return ans;
	}
	

	

}
