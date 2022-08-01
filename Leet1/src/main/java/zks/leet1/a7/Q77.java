package zks.leet1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
77. 组合
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。



示例 1：

输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
示例 2：

输入：n = 1, k = 1
输出：[[1]]


提示：

1 <= n <= 20
1 <= k <= n
 */
public class Q77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] flags = new boolean[n + 1];
        combine(ans,new ArrayList<>(),1,n,k);
        return ans;
    }

    private void combine(List<List<Integer>> ans, List<Integer> list, int i, int n, int k) {
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (; i <= n; i++) {
            list.add(i);
            combine(ans, list, i+1, n, k);
            list.remove(list.size() - 1);
        }
    }

    private void recursion(List<List<Integer>> ans, List<Integer> list, boolean[] flags, int i, int n, int k) {
        if (list.size() == k) {
            ans.add(list);
            return;
        }
        for (; i <= n; i++) {
            List<Integer> l = new ArrayList<>(list);
            if (!flags[i]) {
                flags[i] = true;
                l.add(i);
                recursion(ans, l, flags, i, n, k);
                flags[i] = false;
            }
        }
    }

    @Test
    public void T77() {
        List<List<Integer>> ans = combine(4, 2);
        ans.forEach(System.out::println);
    }

    @Test
    public void T0() {

        List<Integer> list = new ArrayList<>(Arrays.asList(4, 5, 6));
        list.add(0);
        System.out.println(list);
    }

}
