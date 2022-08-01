package zks.leet1.a7;

import java.util.Arrays;

/*
63. 不同路径 II
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。



示例 1：


输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
示例 2：


输入：obstacleGrid = [[0,1],[0,0]]
输出：1


提示：

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] 为 0 或 1
 */
public class Q63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] aA = new int[m][n];//auxiliaryArray 辅助数组,将它初始化为-1
        for (int i = 0; i < m; i++) Arrays.fill(aA[i], -1);
        return recursion(obstacleGrid, aA, 0, 0);
    }

    private int recursion(int[][] obstacleGrid, int[][] aA, int r, int c) {
        if (aA[r][c] >= 0) return aA[r][c];//直接查表获得这个点的答案
        if (r == obstacleGrid.length - 1) {//走到了最下面的一排,这时只需要看机器人右方是否有障碍物,有就是0,没有就是1
            int ans = 1;
            for (int i = c; i < obstacleGrid[0].length; i++) {
                if (obstacleGrid[r][i] > 0) {
                    ans = 0;
                    break;
                }
            }
            aA[r][c] = ans;
            return ans;
        } else if (c == obstacleGrid[0].length - 1) {//和上面对称的情况,走到了最右边一排,看它下方有没有障碍物
            int ans = 1;
            for (int i = r; i < obstacleGrid.length; i++) {
                if (obstacleGrid[i][c] > 0) {
                    ans = 0;
                    break;
                }
            }
            aA[r][c] = ans;
            return ans;
        }
        //若不是平凡情况, 递归
        //这时机器人右方和下方都有元素的,判断
        int right = obstacleGrid[r][c + 1], down = obstacleGrid[r + 1][c];
        if (right == 1 && down == 1) {
            int ans = 0;
            aA[r][c] = ans;
            return ans;
        } else if (right == 1) {//右边有石头,只能向下
            int ans = recursion(obstacleGrid, aA, r + 1, c);
            aA[r][c] = ans;
            return ans;
        } else if (down == 1) {//下面有石头,只能向右
            int ans = recursion(obstacleGrid, aA, r, c + 1);
            aA[r][c] = ans;
            return ans;
        } else {//既能向下又能向右
            int ans = recursion(obstacleGrid, aA, r + 1, c) + recursion(obstacleGrid, aA, r, c + 1);
            aA[r][c] = ans;
            return ans;
        }


    }
}
