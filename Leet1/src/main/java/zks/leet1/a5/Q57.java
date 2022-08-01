package zks.leet1.a6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/*
57. 插入区间
给你一个 无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。



示例 1：

输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
输出：[[1,5],[6,9]]
示例 2：

输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
示例 3：

输入：intervals = [], newInterval = [5,7]
输出：[[5,7]]
示例 4：

输入：intervals = [[1,5]], newInterval = [2,3]
输出：[[1,5]]
示例 5：

输入：intervals = [[1,5]], newInterval = [2,7]
输出：[[1,7]]
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals 根据 intervals[i][0] 按 升序 排列
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/insert-interval
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //将newInterval插入
        int[][] newIntervals = new int[intervals.length + 1][2];
        int i;
        for (i = 0; i < intervals.length; i++) {
            newIntervals[i][0] = intervals[i][0];
            newIntervals[i][1] = intervals[i][1];
        }
        newIntervals[i][0] = newInterval[0];
        newIntervals[i][1] = newInterval[1];
        //然后复用上一题的代码
        return merge(newIntervals);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        ArrayList<int[]> ans = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i < intervals.length - 1 && intervals[i][1] >= intervals[i + 1][0]) {
                //合并,合并的结果存放在intervals[i+1]中
                intervals[i + 1][0] = Math.min(intervals[i][0], intervals[i + 1][0]);
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            } else {
                //下一个不能和它合并,加入ans中
                ans.add(intervals[i]);
            }
        }
        int[][] ints = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ints[i][0] = ans.get(i)[0];
            ints[i][1] = ans.get(i)[1];
        }
        return ints;
    }

    @Test
    public void T57() {
        int[][] intervals = {{1, 2}};
        int[] newInterval = {4, 8};
        int[][] ans = this.insert(intervals, newInterval);
        for (int[] ints : ans) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
