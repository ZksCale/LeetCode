package zks.leet1.a7;

/*
70. 爬楼梯
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？



示例 1：

输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶
示例 2：

输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶
 */
public class Q70 {
    /*    public int climbStairs(int n) {
            int[] nums = new int[n+1];
            return cs(n, nums);
        }

        private int cs(int n, int[] nums) {
            if (nums[n] != 0) return nums[n];
            int ans = 0;
            if (n <= 2) {
                ans = n;
                nums[n]=ans;
                return ans;
            }
            ans = cs(n - 2, nums) + cs(n - 1, nums);
            nums[n] = ans;
            return ans;
        }*/
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int t = b;
            b = a + b;
            a = t;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println("中文字符");
    }
}
