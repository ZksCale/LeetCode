package zks.leet1.a0;

import java.util.LinkedList;

/* 字符串转换整数
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

函数 myAtoi(string s) 的算法如下：

读入字符串并丢弃无用的前导空格
检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
返回整数作为最终结果。
注意：

本题中的空白字符只包括空格字符 ' ' 。
除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 

示例 1：

输入：s = "42"
输出：42
解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
第 1 步："42"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："42"（读入 "42"）
           ^
解析得到整数 42 。
由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
示例 2：

输入：s = "   -42"
输出：-42
解释：
第 1 步："   -42"（读入前导空格，但忽视掉）
            ^
第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
             ^
第 3 步："   -42"（读入 "42"）
               ^
解析得到整数 -42 。
由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
示例 3：

输入：s = "4193 with words"
输出：4193
解释：
第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
             ^
解析得到整数 4193 。
由于 "4193" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 4193 。
 

提示：

0 <= s.length <= 200
s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/string-to-integer-atoi
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q8 {
	public static void main(String[] args) {
		Q8 q = new Q8();
//		char c = '0';
//		System.out.println((int)(c-48));
//		System.out.println((int)('9'-48));
		String s0 = "9223372036854775819";
		String s1 = "      -42";
		String s2 = "4193 with words";
		String s3 = "95342399999964665496865416546499";
		String s4 = "abc";
		System.out.println(s0);
		System.out.println(q.myAtoi(s0));
		System.out.println(s1);
		System.out.println(q.myAtoi(s1));
		System.out.println(s2);
		System.out.println(q.myAtoi(s2));
		System.out.println(s3);
		System.out.println(q.myAtoi(s3));
		System.out.println(s4);
		System.out.println(q.myAtoi(s4));
	}

	public int myAtoi(String s) {
		if (s.equals("")) {
			return 0;
		}
		StringBuilder sb = new StringBuilder(s);
		long ansr = 0;
		int syb = 1;
		LinkedList<Integer> ll = new LinkedList<>();
		// 清除空格
		while (sb.length() > 0 && sb.charAt(0) == ' ') {
			sb.deleteCharAt(0);
		}
		if (sb.length() == 0) {
			return 0;
		} // 读符号
		if (sb.charAt(0) == '-') {
			syb = -1;
			sb.deleteCharAt(0);
		} else if (sb.charAt(0) == '+') {
			sb.deleteCharAt(0);
		} // 读数
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) < '0' || sb.charAt(i) > '9') {
				break;
			}
			ll.addLast((int) (sb.charAt(i)) - 48);
		} // 得出答案
		if (syb > 0) {
			for (int i : ll) {
				ansr *= 10;
				ansr += i;
				if (ansr >= (int) (2e31 - 1)) {
					return (int) (2e31 - 1);
				}
			}
		} else {
			for (int i : ll) {
				ansr *= 10;
				ansr -= i;
				if(ansr <= (int)(-2e31)) {
					return (int)(-2e31);
				}
			}
		}
		return (int) ansr;
	}
}
