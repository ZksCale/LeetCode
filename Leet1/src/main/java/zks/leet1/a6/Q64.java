package zks.leet1.a6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/*
64. 最小路径和
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。



示例 1：


输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
示例 2：

输入：grid = [[1,2,3],[4,5,6]]
输出：12


提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100
 */
public class Q64 {
    /*
    想法有两个,一个是和上两个问题相似,递归加上动态规划,复杂度是建表和查表的过程,大致是O(mn)
    另一个是Dijkstra算法,看成一个方格状的图,该算法从左上角移动到右上角,也许不需要填满一张表就得到答案,时间效率上高于递归
     */
/*    //那么首先是递归
    public int minPathSum(int[][] grid) {
        //建立表,并调用递归函数
        int[][] aA = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) Arrays.fill(aA[i], -1);
        aA[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        return recursion(grid, aA, 0, 0);
    }

    private int recursion(int[][] grid, int[][] aA, int r, int c) {
        //尝试查表,直接获得答案
        if (aA[r][c] >= 0) return aA[r][c];
        //表中没有答案时,计算
        //平凡情况
        if (r == grid.length - 1) {
            int ans = recursion(grid, aA, r, c + 1) + grid[r][c];
            aA[r][c] = ans;
            return ans;
        }
        if (c == grid[0].length - 1) {
            int ans = recursion(grid, aA, r + 1, c) + grid[r][c];
            aA[r][c] = ans;
            return ans;
        }
        //一般情况
        int ans = Math.min(recursion(grid, aA, r + 1, c), recursion(grid, aA, r, c + 1)) + grid[r][c];
        aA[r][c] = ans;
        return ans;
    }*/
//    Dijkstra算法 (图的最短路径算法)
    /*对于数组{{1, 2, 3}, {4, 5, 6}},可以画成一张有向图,数组中的值记录了相邻的点到它的这条边的权重,只能向右或者向下,这也就是边的方向
    O A B C   <--图中的节点是这样排列的
      D E F
    从虚拟的节点O出发,O位于数组左上角元素A的左边,那么边(O,A)长度为1,同样,所有向右的边的权重都可以标记出来:
    (A,B)=2; (B,C)=3; (D,E)=5; (E,F)=6;
    图中还有向下的边, 这种边的权重也是等于表中对应的数字:
    (A,D)=4; (B,E)=5; (C,F)=6;
    将这个表格视为有向图后,确实是可以适用最短路径算法的
     */
    //这个实现存在问题,实现时并没有选好点集和边集的实现方式,导致选择最短路径树生长时出现诸多问题
    //下次实现Dijkstra算法时要定义class点,边
    public int minPathSum(int[][] grid) {//m*n, 想象一个虚拟的起点O位于左上角元素的左边,它到这个点的边的权重为grid[0][0]
        int m = grid.length, n = grid[0].length;
        //S(i,j)表示从虚拟的起点O到这个点的最短路径的长度,这个函数应该返回的值为: S(m-1,n-1)
        //点集S中的元素全都计算好了S(i,j)值,S(-1,0)=0,不停地选择恰好有一个端点位于点集内的边中,S(i,j)+权重最小的那个边,将选择的新节点加入S中,并给定它的值
        //重复上一过程,直至计算出了S(m-1,n-1)
        HashMap<ArrayList<Integer>, Integer> S = new HashMap<>();//集合S
        HashMap<ArrayList<Integer>, Integer> edges = new HashMap<>();//集合S的临边的集合,边(A,B) 存储为: (Ai,Aj,Bi,Bj)->weight
        S.put(new ArrayList<>(Arrays.asList(0, -1)), 0);//放入起点
        edges.put(new ArrayList<>(Arrays.asList(0, -1, 0, 0)), grid[0][0]);//放入起点出发唯一的那条边
        ArrayList<Integer> targetPoint = new ArrayList<>(Arrays.asList(m - 1, n - 1));//终点
        while (!S.containsKey(targetPoint)) {
            //遍历边集,找到边集中S(i,j)+weight最小的那条边
            Iterator<ArrayList<Integer>> iterator = edges.keySet().iterator();
            ArrayList<Integer> nextEdge = iterator.next();
            while (iterator.hasNext()) {
                ArrayList<Integer> edge = iterator.next();
                if (S.get(new ArrayList<>(Arrays.asList(nextEdge.get(0), nextEdge.get(1)))) + edges.get(nextEdge) >
                        S.get(new ArrayList<>(Arrays.asList(edge.get(0), edge.get(1)))) + edges.get(edge))
                    nextEdge = edge;//nextEdge指向下一个要加入的边
            }
            //将它对应的点放入S中
            int Ai = nextEdge.get(0), Aj = nextEdge.get(1), Bi = nextEdge.get(2), Bj = nextEdge.get(3);
            S.put(new ArrayList<>(Arrays.asList(Bi, Bj)), S.get(new ArrayList<>(Arrays.asList(Ai, Aj))) + grid[Bi][Bj]);
            //更新边集, S中新增一个点(Bi,Bj),最多可能导致删除边集中的四条边,也就是它上下左右相连的四条边
            ArrayList<Integer> up = new ArrayList<>(Arrays.asList(Bi - 1, Bj));
            ArrayList<Integer> down = new ArrayList<>(Arrays.asList(Bi + 1, Bj));
            ArrayList<Integer> left = new ArrayList<>(Arrays.asList(Bi, Bj - 1));
            ArrayList<Integer> right = new ArrayList<>(Arrays.asList(Bi, Bj + 1));
            if (S.containsKey(up)) edges.remove(new ArrayList<>(Arrays.asList(Bi - 1, Bj, Bi, Bj)));
            if (S.containsKey(down)) edges.remove(new ArrayList<>(Arrays.asList(Bi, Bj, Bi + 1, Bj)));
            if (S.containsKey(left)) edges.remove(new ArrayList<>(Arrays.asList(Bi, Bj - 1, Bi, Bj)));
            if (S.containsKey(right)) edges.remove(new ArrayList<>(Arrays.asList(Bi, Bj, Bi, Bj + 1)));

            //下方有边
            if (Bi < m - 1) edges.put(new ArrayList<>(Arrays.asList(Bi, Bj, Bi + 1, Bj)), grid[Bi + 1][Bj]);
            //右方有边
            if (Bj < n - 1) edges.put(new ArrayList<>(Arrays.asList(Bi, Bj, Bi, Bj + 1)), grid[Bi][Bj + 1]);
        }
        return S.get(targetPoint);
    }

    //[[1,4,8,6,2,2,1,7],
// [4,7,3,1,4,5,5,1],
// [8,8,2,1,1,8,0,1],
// [8,9,2,9,8,0,8,9],
// [5,7,5,7,1,8,5,5],
// [7,0,9,4,5,6,5,6],
// [4,9,9,7,9,1,9,0]]
    //预期结果 47
    @Test
    public void T64() {
        int[][] grid = new int[][]{
                {1, 4, 8, 6, 2, 2, 1, 7},
                {4, 7, 3, 1, 4, 5, 5, 1},
                {8, 8, 2, 1, 1, 8, 0, 1},
                {8, 9, 2, 9, 8, 0, 8, 9},
                {5, 7, 5, 7, 1, 8, 5, 5},
                {7, 0, 9, 4, 5, 6, 5, 6},
                {4, 9, 9, 7, 9, 1, 9, 0}};
        int ans = this.minPathSum(grid);
        System.out.println(ans);
    }

}
