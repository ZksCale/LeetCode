package zks.leet1.a0;
/*
 * 
 */

public class tTest {
	public static void main(String[] args) {
		String s0 = "program";
		String s1 = "algorithm";
		System.out.println(getLongestCommonSubString(s0, s1));
	}

	private static String getLongestCommonSubString(String s0, String s1) {
		if (s0 == null || s1 == null || s0 == "" || s1 == "") {
			return "";
		}
		if (s0.charAt(s0.length() - 1) == s1.charAt(s1.length() - 1)) {
			return getLongestCommonSubString(s0.substring(0, s0.length() - 1), s1.substring(0, s1.length() - 1))
					+ s0.charAt(s0.length() - 1);
		} else {
			String st0 = getLongestCommonSubString(s0.substring(0, s0.length() - 1), s1);
			String st1 = getLongestCommonSubString(s0, s1.substring(0, s1.length() - 1));
			return st0.length() > st1.length() ? st0 : st1;
		}
	}
}
