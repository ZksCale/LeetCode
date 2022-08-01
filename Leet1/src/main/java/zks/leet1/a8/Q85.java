package zks.leet1.a8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
85. 最大矩形
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。



示例 1：


输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = [["0"]]
输出：0
示例 4：

输入：matrix = [["1"]]
输出：1
示例 5：

输入：matrix = [["0","0"]]
输出：0


提示：

rows == matrix.length
cols == matrix[0].length
1 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'
通过次数135,080提交次数253,461
 */
public class Q85 {

    //递归版本, 效率很低, 用动态规划优化依旧不行
    public int maximalRectangle0(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] table = new int[matrix.length + 1][matrix.length];
        for (int i = 1; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++)
                table[i][j] = -1;
        }
        return maxArea(matrix, 0, matrix.length, table);
    }

    private int maxArea(char[][] matrix, int m, int h, int[][] table) {
        if (h == 0) return 0;
        if (m >= matrix.length) return 0;
        if (h == 1) {
            //平凡情况
            if (table[h][m] >= 0) return table[h][m];
            int ans = 0, t = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[m][i] == '1') t++;
                else {
                    ans = Math.max(ans, t);
                    t = 0;
                }
            }
            ans = Math.max(ans, t);
            table[h][m] = ans;
            return ans;
        } else {
            if (table[h][m] >= 0) return table[h][m];
            //一般情况
            //首先求出高度为h的矩形的最大面积
            int area = 0, t = 0;
            label:
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < h; j++) {
                    if (matrix[m + j][i] == '0') {
                        area = Math.max(area, t);
                        t = 0;
                        continue label;
                    }
                }
                t += h;
            }
            area = Math.max(area, t);
            //然后递归调用
            int ans = Math.max(area, Math.max(maxArea(matrix, m, h - 1, table), maxArea(matrix, m + 1, h - 1, table)));
            table[h][m] = ans;
            return ans;
        }
    }

    public int maximalRectangle(char[][] matrix) {
        //题解,利用Q84的经验,将矩阵转换成柱状图,再通过单调栈优化可解 复杂度O(mn) m层,每层计算面积为O(n)
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        List<Integer> areas = new ArrayList<>();
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            //现在,问题转化为了柱状图中求最大矩形面积
            LinkedList<Integer> stack = new LinkedList<>();
            int[] hl = new int[matrix[0].length];
            int[] hr = new int[matrix[0].length];
            int area = 0;
            for (int k = 0; k < heights.length; k++) {
                if (stack.isEmpty()) {
                    stack.addFirst(k);
                    hl[k] = -1;
                } else {
                    while (!stack.isEmpty() && heights[stack.getFirst()] > heights[k]) {
                        hr[stack.removeFirst()] = k;
                    }
                    hl[k] = stack.isEmpty() ? -1 : stack.getFirst();
                    stack.addFirst(k);
                }
            }
            while (!stack.isEmpty()) {
                Integer r = stack.removeFirst();
                hr[r] = heights.length;
            }
            for (int k = 0; k < heights.length; k++) {
                area = Math.max(area, (hr[k] - hl[k] - 1) * heights[k]);
            }
            areas.add(area);
        }
        int ans = 0;
        for (Integer area : areas) {
            ans = Math.max(ans, area);
        }
        return ans;
    }

    @Test
    public void t85() {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        int i = this.maximalRectangle(matrix);
        System.out.println(i);
    }
}
