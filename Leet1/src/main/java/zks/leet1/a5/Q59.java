package zks.leet1.a5;

import org.junit.jupiter.api.Test;

/*
59. 螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。



示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]

 */
public class Q59 {
    /*
    想象一个王八从[0][-1]处出发向右爬,首先它要看前面是路还是墙,也就是说是0还是正数,如果前面是0就前进,并把脚下的0填上数字,如果前方是数字,就右转前进
    本问题中,前进步数固定为n*n,就用这个来控制循环终止即可
     */
    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        private Direction turnRight() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
            }
            return UP;
        }

        //提供当前坐标以及前进方向,返回能否前进
        private boolean moveForward(int[][] matrix, int r, int c, Direction direction) {
            int n = matrix.length;
            switch (direction) {
                case DOWN:
                    return r + 1 < n && matrix[r + 1][c] == 0;
                case UP:
                    return r - 1 > -1 && matrix[r - 1][c] == 0;
                case LEFT:
                    return c - 1 > -1 && matrix[r][c - 1] == 0;
                case RIGHT:
                    return c + 1 < n && matrix[r][c + 1] == 0;
            }
            return true;
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int r = 0, c = -1;//起点
        Direction direction = Direction.RIGHT;//方向向右
        for (int i = 1; i <= n * n; i++) {
            //先看,若不能前进,则调整方向
            if (!direction.moveForward(ans, r, c, direction)) direction = direction.turnRight();
            switch (direction) {//前进
                case UP:
                    r--;
                    break;
                case DOWN:
                    r++;
                    break;
                case LEFT:
                    c--;
                    break;
                case RIGHT:
                    c++;
                    break;
            }
            ans[r][c] = i;//填数
        }
        return ans;
    }

    @Test
    public void T59() {
        this.generateMatrix(4);
    }
}
