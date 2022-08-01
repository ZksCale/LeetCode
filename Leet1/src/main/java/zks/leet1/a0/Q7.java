package zks.leet1.a1;

import java.util.Iterator;
import java.util.LinkedList;

/*整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。
 

示例 1：

输入：x = 123
输出：321
示例 2：

输入：x = -123
输出：-321
示例 3：

输入：x = 120
输出：21
示例 4：

输入：x = 0
输出：0
 

提示：

-2^31 <= x <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q7 {
	public static void main(String[] args) {
		Q7 q = new Q7();
		int a = 1534236469;
		long x = 95342399999964699l;
		int b = -123456, c = 120, d = 0;
		System.out.println(x > (int)(2e31 -1));
		System.out.println(a + "  " + q.reverse(a));
		System.out.println(b + "  " + q.reverse(b));
		System.out.println(c + "  " + q.reverse(c));
		System.out.println(d + "  " + q.reverse(d));
		System.out.println(-1/10);
	}
	public int reverse(int x) {	
		long xx = x;
		int sym = 1;
		if(xx < 0) {
			xx = -xx;
			sym = -1;
		}
		LinkedList<Integer> ll = new LinkedList<>();
		while(xx > 0) {
			ll.addLast((int)(xx % 10));
			xx /= 10;
		}
		Iterator<Integer> itr = ll.iterator();
		long ans = 0;
		while(itr.hasNext()) {
			ans *= 10;
			ans += itr.next();
		}
		if(ans > (int)(2e31 -1) || ans < (int)(-2e31)) {
			return 0;
		}else {
			return (int)(sym * ans);
		}
		
    }
}
