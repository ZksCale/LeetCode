package zks.leet1.a6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/*
56. 合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。



示例 1：

输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2：

输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。


提示：

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
public class Q56 {
    /*
        对intervals中的元素i0(l0,r0)与i1(l1,r1)定义一个相对的大小关系:
        若l0<l1,则 i0<i1
        若l0==l1,则比较r0与r1,若r0<r1,则i0<i1 若r0==r1,则两者相等
        使用这个大小关系进行排序,那么可以合并的区间一定相邻
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        ArrayList<int[]> ans = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i < intervals.length - 1 && intervals[i][1] >= intervals[i + 1][0]) {
                //合并,合并的结果存放在intervals[i+1]中
                intervals[i + 1][0] = Math.min(intervals[i][0],intervals[i+1][0]);
                intervals[i + 1][1] = Math.max(intervals[i][1],intervals[i+1][1]);
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
    public void T56() {
        int[][] ints=this.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        for(int[] i:ints){
            System.out.println(Arrays.toString(i));
        }
    }
}
