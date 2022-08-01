package zks.leet1.a5;

import org.junit.jupiter.api.Test;

/*
50. Pow(x, n)
实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。



示例 1：

输入：x = 2.00000, n = 10
输出：1024.00000
示例 2：

输入：x = 2.10000, n = 3
输出：9.26100
示例 3：

输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25


提示：

-100.0 < x < 100.0
-231 <= n <= 231-1
-104 <= xn <= 104
 */
public class Q50 {
    /*    public double myPow(double x, int n) {
    //        return Math.pow(x,n); //这样做当然不行
            //递归基
            if (n == 0) return 1.0;
            else if (n == 1) return x;
            else if (n == -1) return 1 / x;
            //更一般的情况有 n为偶数时,pow(x,n) = pow(x*x,n/2)
            //            n为奇数时,pow(x,n)=x*pow(x,n-1)
            if (n % 2 == 0) return myPow(x * x, n >> 1);
            else return x * myPow(x, n - 1);
        }*/
    //迭代版本，对n进行二进制拆分，如77=64+8+4+1
    public double myPow(double x, int n) {

        return n >= 0 ? mpow(x, n) : 1.0 / mpow(x, -n);
    }

    private double mpow(double x, int n) {
        double contribute = x;
        double ans = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) ans *= contribute;
            contribute *= contribute;
            n >>= 1;
        }
        return ans;
    }
    @Test
    public void T50(){
        System.out.println(this.myPow(2,10));
    }
}

