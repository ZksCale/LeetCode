package zks.leet1.a3;

import org.junit.jupiter.api.Test;

/*
36. 有效的数独
请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）


注意：

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
空白格用 '.' 表示。


示例 1：


输入：board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
示例 2：

输入：board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。


提示：

board.length == 9
board[i].length == 9
board[i][j] 是一位数字（1-9）或者 '.'
 */
public class Q36 {
    public boolean isValidSudoku(char[][] board) {
        //行检查,列检查,九宫格检查
        return this.rowCheck(board) && this.columnCheck(board) && this.gridCheck(board);

    }

    private boolean rowCheck(char[][] board) {
        for (char[] c : board) if (!this.sudokuCheck(c)) return false;
        return true;
    }

    private boolean columnCheck(char[][] board) {
        for (int i = 0; i < 9; i++) {
            char[] nums = new char[9];
            for (int j = 0; j < 9; j++) nums[j] = board[j][i];
            if (!this.sudokuCheck(nums)) return false;
        }
        return true;
    }

    private boolean gridCheck(char[][] board) {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                char[] nums = new char[9];
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++)
                        nums[a * 3 + b] = board[i + a][j + b];
                }
                if (!this.sudokuCheck(nums)) return false;
            }
        }
        return true;
    }

    private int[] n = new int[10];//辅助数组

    //检查九个数组成的char[]是否是有效的数独
    private boolean sudokuCheck(char[] nums) {
        //将辅助数组重置
        for (int i = 0; i <= 9; i++) this.n[i] = 0;
        for (int i = 0; i < 9; i++) {
            if (nums[i] == '.') continue;
            int index = nums[i] - '0';
            if (n[index] != 0) return false;
            n[index] = 1;
        }
        return true;
    }


    @Test
    public void T36() {
        String[][] sboard = new String[][]{
                {"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"},
        };
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sboard[i][j].charAt(0);
            }
        }
        System.out.println(this.isValidSudoku(board));


    }
}
