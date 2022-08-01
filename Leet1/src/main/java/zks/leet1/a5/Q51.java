package zks.leet1.a6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
51. N 皇后
n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。



示例 1：


输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。
示例 2：

输入：n = 1
输出：[["Q"]]
 */
public class Q51 {
    //一个boolean数组,用来记录对应位置是否有效,true表示这个位置是可以放入皇后的
    private boolean[][] isValid;

    public List<List<String>> solveNQueens(int n) {
        isValid = new boolean[n][n];
        for (int i = 0; i < n; i++)//初始化辅助数组
            Arrays.fill(isValid[i], true);
        List<List<String>> ans = new ArrayList<>();
        this.backtrack(ans, new ArrayList<>(), 0, n);
        return ans;
    }

    //回溯算法, 执行后,ans中应该装入了对应n皇后的所有解
    private void backtrack(List<List<String>> ans, List<String> board, int row, int n) {
        //若当前的board成功地在每一行都放入了一个皇后,那么将这个答案存放在ans中
        if (row >= n) {
            ans.add(new ArrayList<>(board));
            return;
        }

        for (int i = 0; i < n; i++) {//遍历第row行
            if(!isValid[row][i]) continue;

            //index是一个[0,n)之内的值
            //下面放入Q,并递归调用下一行,尝试搜索出答案
            char[] chars = new char[n];
            for (int j = 0; j < n; j++) {
                if (j == i) chars[j] = 'Q';
                else chars[j] = '.';
            }
            board.add(new String(chars));
            //调整isValid
            //row行
            isValid[row][i] = false;
            ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>();
            //(row,n)行,Q所在的位置向左下\下方\右下 前进,将碰到的每个true都置为false
            int r = row, c = i;//左下
            while (--c >= 0 && ++r < n) {
                if (isValid[r][c]) {
                    isValid[r][c] = false;
                    //每次确实地更改了一个元素,将rc对储存,用于回溯
                    coordinates.add(new ArrayList<>(Arrays.asList(r, c)));
                }
            }
            r = row;//正下
            while (++r < n) {
                if (isValid[r][i]) {
                    isValid[r][i] = false;
                    coordinates.add(new ArrayList<>(Arrays.asList(r, i)));
                }
            }
            // 右下
            r = row;
            c = i;
            while (++r < n && ++c < n) {
                if (isValid[r][c]) {
                    isValid[r][c] = false;
                    coordinates.add(new ArrayList<>(Arrays.asList(r, c)));
                }
            }//递归
            backtrack(ans, board, row + 1, n);
            //然后回溯
            //调整board
            board.remove(row);
            //调整isValid
            isValid[row][i] = true;//row行
            for (ArrayList<Integer> coordinate : coordinates) //(row,n)行
                isValid[coordinate.get(0)][coordinate.get(1)] = true;

        }
    }

    //
    @Test
    public void T51() {
        int n = 5;
        System.out.println(this.solveNQueens(n));
    }
}
