package zks.leet1.a6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？



示例 1：


输入：m = 3, n = 7
输出：28
示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下
示例 3：

输入：m = 7, n = 3
输出：28
示例 4：

输入：m = 3, n = 3
输出：6


提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 109
 */
public class Q62 {
    /*递归实现起来是很简单的,但是效率并不好
    函数up(m,n)的平凡情况:m,n中有一个为1时,ans=1
    m,n都大于1时,答案是return up(m-1,n) + up(m,n-1)递归即可求解

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

     */
    /*
    考虑将它优化,能观察到的是,计算了非常多次同样的up(m,n),使用一个表记录下来
     */
    private HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        ArrayList<Integer> a=new ArrayList<>(Arrays.asList(m,n));
        if(map.containsKey(a)) return map.get(a);
        else{
            int ans=uniquePaths(m-1,n)+uniquePaths(m,n-1);
            map.put(a,ans);
            return ans;
        }
    }
}
