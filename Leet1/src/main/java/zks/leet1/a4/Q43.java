package zks.leet1.a5;

import org.junit.jupiter.api.Test;

/*
43. 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。



示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"


提示：

1 <= num1.length, num2.length <= 200
num1 和 num2 只能由数字组成。
num1 和 num2 都不包含任何前导零，除了数字0本身。
 */
public class Q43 {
    //就像乘法竖式那样计算 123*456=123*400+123*50+123*6
    //num2中的每一位都乘num1,结果保存在一个数组中,这个数组长度应该是num2.length()
    //然后对这个数组进行移位以及求和,得到最终答案
    //时间复杂度:O(m*n+n*n)
    //空间复杂度:O(n)
    public String multiply(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        if (num1.length() < num2.length()) {
            String s = num1;
            num1 = num2;
            num2 = s;
        }
        int l1 = num1.length();
        int l2 = num2.length();
        String sum = "0";//要计算加法,是正向的
        for (int i = 0; i < l2; i++) {//num2从左向右扫描,每个数都乘以num1,sum乘10后和这轮扫描的结果相加,最终sum
            int carrier = 0;
            int n2 = num2.charAt(i) - '0';
            StringBuilder middle = new StringBuilder();
            for (int j = l1 - 1; j >= 0; j--) {
                int n1 = num1.charAt(j) - '0';
                int n = n1 * n2 + carrier;
                if (n > 9) {
                    carrier = n / 10;
                    n %= 10;
                } else carrier = 0;
                middle.append(n);
            }
            if (carrier != 0) middle.append(carrier);
            middle.reverse();
            sum = this.addString(this.multiply10(sum), new String(middle));
        }
        return sum;
    }

    private String multiply10(String num) {
        if (num.equals("0")) return "0";
        return num + "0";
    }


    //将两个字符串表示的数字相加
    public String addString(String num1, String num2) {
        if (num2.charAt(0) == '0' && num2.length() > 1) num2 = "0";
        if (num1.charAt(0) == '0' && num1.length() > 1) num1 = "0";
        StringBuilder ans = new StringBuilder();
        if (num1.length() < num2.length()) {
            String s = num1;
            num1 = num2;
            num2 = s;
        }
        int i;
        int l1 = num1.length();
        int l2 = num2.length();
        boolean carryFlag = false;
        for (i = 1; i <= l2; i++) {
            int n1 = num1.charAt(l1 - i) - '0';
            int n2 = num2.charAt(l2 - i) - '0';
            int n = n1 + n2;
            if (carryFlag) {
                n++;
                carryFlag = false;
            }
            if (n >= 10) {
                carryFlag = true;
                n -= 10;
            }
            ans.append(n);
        }
        if (l1 > l2) {
            for (; i <= l1; i++) {
                int n = num1.charAt(l1 - i) - '0';
                if (carryFlag) {
                    carryFlag = false;
                    n++;
                }
                if (n > 9) {
                    carryFlag = true;
                    n -= 10;
                }
                ans.append(n);
            }
        }
        if (carryFlag) ans.append(1);
        ans.reverse();
        return new String(ans);
    }


    @Test
    public void T43() {
        String num1 = "9133";
        String num2 = "0";
        System.out.print(num1 + " * " + num2 + " = ");
        System.out.println(this.multiply(num1, num2));
    }
}
