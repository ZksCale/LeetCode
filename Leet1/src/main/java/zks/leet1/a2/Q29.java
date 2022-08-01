package zks.leet1.a3;

/*
 * 29. 两数相除
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

 

示例 1:

输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
 

提示：

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class Q29 {
	// 本题中边界值处理很费劲,并且没有多少意义,只要能做到正常整除不越界的数就行了
	public static void main(String[] args) {
		int dividend = 12, divisor = 3;
		System.out.println(new Q29().divide(dividend, divisor));
	}

	public int divide(int dividend, int divisor) {
		// 先处理符号
		boolean symbol = true;
		if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
			symbol = false;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		return symbol ? +(divideNoSymbol(dividend, divisor)) : -(divideNoSymbol(dividend, divisor));
//        if(dividend==-2147483648 && divisor==-1)return 2147483647;
//        return dividend/divisor;
	}

	private static int divideNoSymbol(int dividend, int divisor) {
		if (dividend < divisor) {
			return 0;
		} else {
			int i = 1;
			int d = divisor;
			while (dividend < d >> 1) {
				d >>= 1;
				i >>= 1;
			}
			return i + divideNoSymbol(dividend - d, divisor);
		}

	}
}
