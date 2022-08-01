package zks.leet1.a7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
74. 搜索二维矩阵
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。


示例 1：


输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
示例 2：


输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
 */
public class Q74 {
    /*
    直观的想法是,确定定位在哪一行, 然后在哪一行中再进行搜索
    竖着看的话,每一列也可看成是一个升序数组,所以调用两次二分查找可以得出解
    O(log(m+n))
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = searchInFirstColumn(matrix, target);
        if (i < 0) return false;
        int ans = Arrays.binarySearch(matrix[i], target);
        return ans >= 0;
    }

    //二分查找的语义约定:返回的i满足: target可能出现的那一行的秩,若i为-1,说明target一定不在矩阵中
    private int searchInFirstColumn(int[][] matrix, int target) {
        if (target < matrix[0][0]) return -1;
        if (target > matrix[matrix.length - 1][matrix[0].length - 1]) return -1;
        if (matrix.length == 1) return 0;
        int lo = 0, hi = matrix.length-1, ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (matrix[mid][0] > target) hi = mid - 1;
            else {
                ans = mid;
                lo = mid + 1;
            }
        }
        return ans;
    }

    @Test
    public void T74() {
//        int[][] matrix = new int[10][10];
//        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) matrix[i][j] = i * 20 + j;
        int[][] matrix = new int[][]{{1},{3}};
        System.out.println(this.searchMatrix(matrix, 3));
    }
}
