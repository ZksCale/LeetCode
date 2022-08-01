package zks.leet1.a7;

import java.util.Arrays;

/*
72. 编辑距离
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符


示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')


提示：

0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成
 */
public class Q72 {
    /*String A,B A花费最少的步数到达B
    定义:D[i][j]表示A的前i个字符组成的子串转换到B的前j个字符组成的子串所需的最小步数
    平凡情况:i,j中有一个为0时,空串转移到另一个串,所需要的步数一定等于另一个串的长度
    一般情况:i,j>0时,D[i][j]取值为以下情况中最小值:
    1) 1+D[i-1][j]
    2) 1+D[i][j-1]
    3) A[i]==B[j] ? 1+D[i-1][j-1] : D[i-1][j-1]
    我们所要求的答案就是整个矩阵最右下角的那个值,为此,确实需要生成整个矩阵才可以计算出
    时间复杂度: O(i*j)
     */
    public int minDistance(String word1, String word2) {
        int i = word1.length(), j = word2.length();
        int[][] D = new int[i + 1][j + 1];
        for (int row = 0; row <= i; row++) Arrays.fill(D[row], -1);//将D中元素初始化为负数
        return recursion(word1, word2, D, i, j);
    }

    private int recursion(String A, String B, int[][] D, int i, int j) {
        if (D[i][j] > -1) return D[i][j];
        if (i == 0) {
            D[i][j] = j;
            return j;
        }
        if (j == 0) {
            D[i][j] = i;
            return i;
        }//下面处理一般情况
        int top = recursion(A, B, D, i - 1, j) + 1;
        int left = recursion(A, B, D, i, j - 1) + 1;
        int topLeft = A.charAt(i - 1) == B.charAt(j - 1) ? recursion(A, B, D, i - 1, j - 1) : recursion(A, B, D, i - 1, j - 1) + 1;
        int ans = Math.min(Math.min(top, left), topLeft);
        D[i][j] = ans;
        return ans;
    }
}
