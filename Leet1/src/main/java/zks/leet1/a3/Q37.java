package zks.leet1.a3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q37 {
    /*
    递归穷举
    递归函数返回一个boolean 当且仅当最后一格成功填入数字后,返回true
    遍历每个空白,在每个空白都调用自身
     */


    public void solveSudoku(char[][] board) {
        // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        // 初始化
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if(1 <= num && num <= 9){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;
                }
            }
        }
        // 递归尝试填充数组
        recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, 0, 0);
    }

    private boolean recusiveSolveSudoku(char[][]board, boolean[][]rowUsed, boolean[][]colUsed, boolean[][][]boxUsed, int row, int col){
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if(col == board[0].length){
            col = 0;
            row++;
            if(row == board.length){
                return true;
            }
        }
        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if(board[row][col] == '.') {
            // 尝试填充1~9
            for(int num = 1; num <= 9; num++){
                boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row/3][col/3][num]);
                if(canUsed){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;

                    board[row][col] = (char)('0' + num);
                    if(recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1)){
                        return true;
                    }
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row/3][col/3][num] = false;
                }
            }
        } else {
            return recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        return false;
    }



    @Test
    public void T37() {
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
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
        this.solveSudoku(board);
        System.out.println("************************************************************");
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    @Test
    public void fT1() {
        /*
 失败的用例:
[".",".","9","7","4","8",".",".","."],
["7",".",".",".",".",".",".",".","."],
[".","2",".","1",".","9",".",".","."],
[".",".","7",".",".",".","2","4","."],
[".","6","4",".","1",".","5","9","."],
[".","9","8",".",".",".","3",".","."],
[".",".",".","8",".","3",".","2","."]
[".",".",".",".",".",".",".",".","6"],
[".",".",".","2","7","5","9",".","."]
         */
        String[][] sboard = new String[][]{
                {".", ".", "9", "7", "4", "8", ".", ".", "."},
                {"7", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", "2", ".", "1", ".", "9", ".", ".", "."},
                {".", ".", "7", ".", ".", ".", "2", "4", "."},
                {".", "6", "4", ".", "1", ".", "5", "9", "."},
                {".", "9", "8", ".", ".", ".", "3", ".", "."},
                {".", ".", ".", "8", ".", "3", ".", "2", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", "6"},
                {".", ".", ".", "2", "7", "5", "9", ".", "."},
        };
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sboard[i][j].charAt(0);
            }
        }
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
        this.solveSudoku(board);
        System.out.println("************************************************************");
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    @Test
    public void fT2() {
        /*
        [".",".",".","2",".",".",".","6","3"],
        ["3",".",".",".",".","5","4",".","1"],
        [".",".","1",".",".","3","9","8","."],
        [".",".",".",".",".",".",".","9","."],
        [".",".",".","5","3","8",".",".","."],
        [".","3",".",".",".",".",".",".","."],
        [".","2","6","3",".",".","5",".","."],
        ["5",".","3","7",".",".",".",".","8"],
        ["4","7",".",".",".","1",".",".","."]
         */
        String[][] sboard = new String[][]{
                {".", ".", ".", "2", ".", ".", ".", "6", "3"},
                {"3", ".", ".", ".", ".", "5", "4", ".", "1"},
                {".", ".", "1", ".", ".", "3", "9", "8", "."},
                {".", ".", ".", ".", ".", ".", ".", "9", "."},
                {".", ".", ".", "5", "3", "8", ".", ".", "."},
                {".", "3", ".", ".", ".", ".", ".", ".", "."},
                {".", "2", "6", "3", ".", ".", "5", ".", "."},
                {"5", ".", "3", "7", ".", ".", ".", ".", "8"},
                {"4", "7", ".", ".", ".", "1", ".", ".", "."},
        };
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sboard[i][j].charAt(0);
            }
        }
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
        this.solveSudoku(board);
        System.out.println("************************************************************");
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    /*
    [["1",".",".",".","7",".",".","3","."],
    ["8","3",".","6",".",".",".",".","."],
    [".",".","2","9",".",".","6",".","8"],
    ["6",".",".",".",".","4","9",".","7"],
    [".","9",".",".",".",".",".","5","."],
    ["3",".","7","5",".",".",".",".","4"],
    ["2",".","3",".",".","9","1",".","."],
    [".",".",".",".",".","2",".","4","3"],
    [".","4",".",".","8",".",".",".","9"]]
     */
    @Test
    public void fT3() {
        String[][] sboard = new String[][]{
                {"1", ".", ".", ".", "7", ".", ".", "3", "."},
                {"8", "3", ".", "6", ".", ".", ".", ".", "."},
                {".", ".", "2", "9", ".", ".", "6", ".", "8"},
                {"6", ".", ".", ".", ".", "4", "9", ".", "7"},
                {".", "9", ".", ".", ".", ".", ".", "5", "."},
                {"3", ".", "7", "5", ".", ".", ".", ".", "4"},
                {"2", ".", "3", ".", ".", "9", "1", ".", "."},
                {".", ".", ".", ".", ".", "2", ".", "4", "3"},
                {".", "4", ".", ".", "8", ".", ".", ".", "9"},
        };
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sboard[i][j].charAt(0);
            }
        }
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
        this.solveSudoku(board);
        System.out.println("************************************************************");
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }
}
