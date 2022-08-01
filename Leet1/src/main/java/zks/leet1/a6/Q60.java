package zks.leet1.a7;

import org.junit.jupiter.api.Test;

/*
60. 排列序列
给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。



示例 1：

输入：n = 3, k = 3
输出："213"
示例 2：

输入：n = 4, k = 9
输出："2314"
示例 3：

输入：n = 3, k = 1
输出："123"


提示：

1 <= n <= 9
1 <= k <= n!
 */
public class Q60 {
    /*
    想象一颗树,根节点是空序列,每向下生长一层就是向序列中增加一个数字,那么对于n,它的全排列就是这颗树的每一片叶子,k就是取这棵树的第"k"片叶子
    这个问题中,不需要为了一片叶子生成整棵树,只需要从根出发,选择一个数字,计算子树中新的数字和k的值
    时间复杂度O(n)
     */
    public String getPermutation(int n, int k) {
        //需要数组[1,n],一个辅助数组,记录数字是否用过
        //k表示第k个排列,也就是第k片叶子,每深入一层,n减1,k要计算新的k值
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        boolean[] used = new boolean[n];//false表示未用过
        StringBuilder ans = new StringBuilder();
        getPermutation(ans, nums, used, n, k);
        return new String(ans);
    }

    /**
     * 返回nums的第k种全排列
     *
     * @param nums [1,n]组成的数组
     * @param used 标记nums中的元素是否被用过,true表示被使用过
     * @param n    "还剩下几个数字"
     * @param k    "第k片叶子"
     * @return
     */
    private void getPermutation(StringBuilder ans, int[] nums, boolean[] used, int n, int k) {
        if (n == 1) {
            int i;
            for (i = 0; i < nums.length; i++) if (!used[i]) break;//找那个没被用过的数字
            ans.append(nums[i]);//加到答案末尾
            return;
        }//n>1时
        //选数
        //计算(n-1)!
        int factorial = 1;
        for (int i = 2; i <= n - 1; i++) factorial *= i;
        int a = (k-1) / factorial + 1;//这一轮应该选nums中从左数第a个可用的数字
        int index = -1;
        int b = a - 1;
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (a == 1) {
                index = i;//那个数字的下标为index
                break;
            }
            a--;
        }
        used[index] = true;//标记
        ans.append(nums[index]);//添加
        k -= b * factorial;//新k
        //递归
        getPermutation(ans, nums, used, n - 1, k);
    }

    @Test
    public void T60() {
        int n = 4, k = 9;
        System.out.println(this.getPermutation(n, k));
    }
}





















