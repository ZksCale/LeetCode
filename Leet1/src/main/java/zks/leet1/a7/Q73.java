package Q70;

import java.util.Arrays;

/*
73. 矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。



示例 1：


输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]
示例 2：


输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]


提示：

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1


进阶：

一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个仅使用常量空间的解决方案吗？
 */
public class Q73 {
    /*
    一个可行的解决方案是,用首行和首列作为标记,使用最小的额外空间
     */
    public void setZeroes(int[][] matrix) {
        boolean rowIs0 = false, columnIs0 = false;
        for (int i = 0; i < matrix.length; i++) {//列检查
            if (matrix[i][0] == 0) {
                rowIs0 = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {//行检查
            if (matrix[0][i] == 0) {
                columnIs0 = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {//标记
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {//置0
            if (matrix[i][0] == 0)
                for (int j = 1; j < matrix[0].length; j++) matrix[i][j] = 0;
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0)
                for (int i = 1; i < matrix.length; i++) matrix[i][j] = 0;
        }
        if (rowIs0) for (int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
        if (columnIs0) Arrays.fill(matrix[0], 0);
    }

}