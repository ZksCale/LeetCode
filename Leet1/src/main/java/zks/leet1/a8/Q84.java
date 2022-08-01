package zks.leet1;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/*
84. 柱状图中最大的矩形
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。



示例 1:



输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
示例 2：



输入： heights = [2,4]
输出： 4


提示：

1 <= heights.length <=105
0 <= heights[i] <= 104
 */
public class Q84 {
    public int largestRectangleArea0(int[] heights) {
        //考虑从左到右遍历柱子,对于柱子i,考察以它的高为矩形的高的面积是多少,经过遍历后可以找到最小面积
        //复杂度: 最坏的情况下每个柱子向左加上向右要扫描n个柱子,总共n个柱子,所以复杂度O(n^2)
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            //对于每个柱子,向左向右观察,确定所能围成的最大矩形的宽,当遇到图形边缘或者遇到比它更矮的柱子即可停止
            //规定,最终包含的区间应该是(l,r)宽度为
            int l = i, r = i;
            while (--l >= 0) {
                if (heights[l] < heights[i]) break;
            }
            while (++r <= heights.length - 1) {
                if (heights[r] < heights[i]) break;
            }

            //面积:
            ans = Math.max((r - l - 1) * heights[i], ans);
        }
        return ans;
    }

    //上述暴力解法超时, 考虑使用单调栈进行优化
    public int largestRectangleArea1(int[] heights) {
        LinkedList<Integer> lStack = new LinkedList<>();
        LinkedList<Integer> rStack = new LinkedList<>();
        //栈中记录下标,栈必须严格递增
        //两个数组,用来记录每个柱子的左右侧柱的下标
        int[] hl = new int[heights.length];
        int[] hr = new int[heights.length];
        //用来装答案
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            hl[i] = addToLStack(lStack, i, heights);
        }
        for (int i = heights.length - 1; i > -1; i--) {
            hr[i] = addToRStack(rStack, i, heights);
        }
        for (int i = 0; i < heights.length; i++) {
            ans = Math.max(ans, (hr[i] - hl[i] - 1) * heights[i]);
        }
        return ans;
    }

    //考虑对上述的单调栈进行常系数优化, 具体来讲,只通过一次从左向右的扫描得到它的左右两个柱子的下标,其实这是可行的.
    public int largestRectangleArea(int[] heights) {
        int[] hl = new int[heights.length];
        int[] hr = new int[heights.length];
        LinkedList<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty()) {
                stack.addFirst(i);
                hl[i] = -1;
            } else {
                while (!stack.isEmpty() && heights[stack.getFirst()] >= heights[i]) {
                    hr[stack.removeFirst()] = i;
                }
                hl[i] = stack.isEmpty() ? -1 : stack.getFirst();
                stack.addFirst(i);
            }
        }
        //循环结束后, 栈中还有元素,这些元素的右柱的下标还没更新
        while (!stack.isEmpty()) {
            hr[stack.removeFirst()] = heights.length;
        }
        for (int i = 0; i < heights.length; i++) {
            ans = Math.max(ans, (hr[i] - hl[i] - 1) * heights[i]);
        }
        return ans;
    }

    private int addToLStack(LinkedList<Integer> stack, int index, int[] heights) {
        if (stack.isEmpty()) {
            stack.addFirst(index);
            return -1;
        } else {
            while (!stack.isEmpty() && heights[stack.getFirst()] >= heights[index]) stack.removeFirst();
            int result = stack.isEmpty() ? -1 : stack.getFirst();
            stack.addFirst(index);
            return result;
        }
    }

    private int addToRStack(LinkedList<Integer> stack, int index, int[] heights) {
        int result = addToLStack(stack, index, heights);
        return result < 0 ? heights.length : result;
    }

    @Test
    public void t84() {
        int[] height = new int[]{6, 7, 5, 2, 4, 5, 9, 3};
        System.out.println(largestRectangleArea(height));
    }

}
