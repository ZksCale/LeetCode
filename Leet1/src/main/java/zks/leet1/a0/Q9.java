package zks.leet1.a0;


/*
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

例如，121 是回文，而 123 不是。
 

示例 1：

输入：x = 121
输出：true
示例 2：

输入：x = -121
输出：false
解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3：

输入：x = 10
输出：false
解释：从右向左读, 为 01 。因此它不是一个回文数。
 

提示：

-2^31 <= x <= 2^31 - 1
 

进阶：你能不将整数转为字符串来解决这个问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q9 {
	public static void main(String[] args) {
		Q9 q = new Q9();
		int a = 121;
		int b = -121;
		int c = 0;
		System.out.println(q.isPalindrome(a));
		System.out.println(q.isPalindrome(b));
		System.out.println(q.isPalindrome(c));
	}

	public boolean isPalindrome(int x) {
		String reverseStr = new String(new StringBuilder(x + "").reverse());
		return (x + "").equals(reverseStr);
	}
//	public boolean isPalindrome(int x) {
//		StringBuilder sb = new StringBuilder();
//		LinkedList<Integer> ll = new LinkedList<Integer>();
//		if (x == (int) (-2e31)) {// 极端情况
//			return false;
//		}
//		// 将x放入ll中, 再将ll元素放入sb中, 然后判断sb和反转后的sb内容是否相等
//		if (x < 0) {
//			return false;
//		}
//		while (x > 0) {
//			ll.addFirst(x % 10);
//			x /= 10;
//		}
//		for (int i : ll) {
//			sb.append(i);
//		}
//		StringBuilder sb2 = new StringBuilder(sb);
//		sb.reverse();
//		return sb.compareTo(sb2) == 0;
//	}

}
