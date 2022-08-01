package zks.leet1.a4;

import org.junit.jupiter.api.Test;

/*
48. 旋转图像
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。



示例 1：


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]
示例 2：


输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


提示：

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */
public class Q48 {
    /*
    对于元素matrix[i][j]将它顺时针旋转九十度,它应该位于matrix[
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            rotate(matrix, i, matrix.length - i * 2);
        }
    }

    //旋转第i层,该层的边长为d
    public void rotate(int[][] matrix, int i, int d) {
        if (d <= 1) return;
        for (int x = 0; x < d - 1; x++) {
            int t = matrix[i][i + x];//0->t
            matrix[i][i + x] = matrix[i + d - 1 - x][i];//3->0
            matrix[i + d - 1 - x][i] = matrix[i + d - 1][i + d - 1 - x];//2->3
            matrix[i + d - 1][i + d - 1 - x] = matrix[i + x][i + d - 1];//1->2
            matrix[i + x][i + d - 1] = t;//t->1
        }
    }

    @Test
    public void T48() {

    }
}
