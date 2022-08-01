package zks.leet1.a2;

import java.util.LinkedList;
import java.util.List;

/*
 * 22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 

示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 2
输出：["(())", "()()"]

输入：n = 1
输出：["()"]
 

提示：

1 <= n <= 8
 */
public class Q22 {
	public static void main(String[] args) {
		Q22 q = new Q22();
		System.out.println(q.generateParenthesis(3));
	}

	LinkedList<String> ans = new LinkedList<>();

	public List<String> generateParenthesis(int n) {
		
		this.dfs(n, n, "");
		return ans;
	}

	private void dfs(int l, int r, String s) {
		if (l == 0 && r == 0) {
			ans.add(s);
			return;
		}
		if (l > 0) {
			dfs(l - 1, r, s + "(");
		}
		if (r > l) {
			dfs(l, r - 1, s + ")");
		}
	}
	// DFS "深度优先搜索"
//	本题证实了数据结构这门课的重要性
	// 经过观察,generateparenthesis函数的答案构成的序列,n和n-1相比,相当于是讲n-1的List中的si:
	// "()"+si/"("+si+")"/si+"()"再去重处理
	// 去重交给hashset,最终的答案放入list中

	/*
	 * public List<String> generateParenthesis(int n) { LinkedList<String> ans = new
	 * LinkedList<String>(); LinkedHashSet<String> set = new
	 * LinkedHashSet<String>(); set.add("()"); while (n > 1) { int size =
	 * set.size(); Iterator<String> it = set.iterator(); for (int i = 0; i < size;
	 * i++) { String s = it.next(); it.remove(); set.add("()" + s); set.add("(" + s
	 * + ")"); set.add(s + "()"); } n--; } ans.addAll(set); return ans; }
	 */

}
