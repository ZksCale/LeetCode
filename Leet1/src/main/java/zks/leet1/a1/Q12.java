package zks.leet1.a2;

import java.util.HashMap;

/*
 * 12. 整数转罗马数字
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给你一个整数，将其转为罗马数字。

 

示例 1:

输入: num = 3
输出: "III"
示例 2:

输入: num = 4
输出: "IV"
示例 3:

输入: num = 9
输出: "IX"
示例 4:

输入: num = 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
示例 5:

输入: num = 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
 

提示：

1 <= num <= 3999

 */
public class Q12 {
	public static void main(String[] args) {
		Q12 q = new Q12();
		int a = 3;
		int b = 4;
		int c = 9;
		int d = 58;
		int e = 1994;
		int num = 3999;
		System.out.println(a + " " + q.intToRoman(a));
		System.out.println(b + " " + q.intToRoman(b));
		System.out.println(c + " " + q.intToRoman(c));
		System.out.println(d + " " + q.intToRoman(d));
		System.out.println(e + " " + q.intToRoman(e));
		System.out.println(num + " " + q.intToRoman(num));
	}

//1~10 I II III IV V VI VII VIII IX X
//11~20 XI XII XIII XIV XV XVI XVII XVIII XIX XX
	// 对原数字从左向右按位读取,每次读取时,将原来的数字乘10,并加上新读取的那一位
	private static HashMap<Character, Character> hmc;//

	static {
		hmc = new HashMap<>();
		hmc.put('I', 'X');
		hmc.put('X', 'C');
		hmc.put('C', 'M');

		hmc.put('V', 'L');
		hmc.put('L', 'D');
	}

	public String intToRoman(int num) {
		String n = new String(num + "");
		StringBuilder ansr = new StringBuilder();
		for (int i = 0; i < n.length(); i++) {
			multiply10(ansr);
			ansr.append(singleNumToRoman(n.charAt(i)));
		}
		return new String(ansr);
	}

	private static String singleNumToRoman(char num) {
		switch (num) {
		case '1':
			return "I";
		case '2':
			return "II";
		case '3':
			return "III";
		case '4':
			return "IV";
		case '5':
			return "V";
		case '6':
			return "VI";
		case '7':
			return "VII";
		case '8':
			return "VIII";
		case '9':
			return "IX";
		default:
			return "";
		}
	}

	private static void multiply10(StringBuilder num) {// I X C M 和 V L D 每次乘10向右移动
		for (int i = 0; i < num.length(); i++) {
			num.setCharAt(i, hmc.get(num.charAt(i)));// 查表并更改num中的值
		}
	}
}
