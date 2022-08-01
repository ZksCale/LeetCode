package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Q54 {
    /*
    54. 螺旋矩阵
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。



示例 1：


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：


输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
通过次数252,216提交次数518,303
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        //这个螺旋遍历中,每一层都会,向右,向下,向左,向上,然后在这一圈最左上角那个点的正下方停下,重新开始下一圈的螺旋遍历
        //只要知道了m和n,这个遍历的过程就是固定的了
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
//        ans.add(matrix[0][0]);
        int r = 0;

        while (true) {
            int _l = 2 * r;
            int i = r, j = r - 1;
            //向右
            if (n - _l <= 0) break;
            for(int a=1;a<=n-_l;a++) ans.add(matrix[i][++j]);
            //向下
            _l++;
            if (m - _l <= 0) break;
            for(int a=1;a<=m-_l;a++) ans.add(matrix[++i][j]);
            //向左
            if (n - _l <= 0) break;
            for(int a=1;a<=n-_l;a++) ans.add(matrix[i][--j]);
            //向上
            _l++;
            if (m - _l <= 0) break;
            for(int a=1;a<=m-_l;a++) ans.add(matrix[--i][j]);
            r++;
        }
        return ans;

    }

    @Test
    public void T54() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(this.spiralOrder(matrix));
    }
}
