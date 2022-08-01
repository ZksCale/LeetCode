package zks.leet1.a7;

import org.junit.jupiter.api.Test;

/*
79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



示例 1：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
示例 2：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true
示例 3：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false


提示：

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成


进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class Q79 {
    //依旧是bfs,找到就可返回true, 全部遍历完才能返回false
    public boolean exist(char[][] board, String word) {
        boolean[][] flags = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (bfs(board, flags, i, j, 1, word)) return true;
            }
        }
        return false;
    }

    //判断在board[i][j]处能否搜索到word
    private boolean bfs(char[][] board, boolean[][] flags, int i, int j, int n, String word) {
        //递归基
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) return false;
        if (flags[i][j]) return false;
//        if (n == word.length()) return true;
        //检查当前格子
        if (word.charAt(n-1) != board[i][j]) return false;
        else {
            if (n == word.length()) return true;
            //尝试上下左右递归
            flags[i][j] = true;
            boolean result = bfs(board, flags, i - 1, j, n + 1, word) ||
                    bfs(board, flags, i + 1, j, n + 1, word) ||
                    bfs(board, flags, i, j - 1, n + 1, word) ||
                    bfs(board, flags, i, j + 1, n + 1, word);
            flags[i][j] = false;
            return result;
        }
    }
    @Test
    public void t79(){
        char[][] board={{'a','b'}};
        String word="ba";
        System.out.println(this.exist(board, word));
    }

}
