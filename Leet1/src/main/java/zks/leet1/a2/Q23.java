package zks.leet1.a2;

import java.util.ArrayList;
import java.util.Collections;

/*
 *
23. 合并K个升序链表
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

 

示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：

输入：lists = []
输出：[]
示例 3：

输入：lists = [[]]
输出：[]
 

提示：

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4
 */
public class Q23 {
	public static void main(String[] args) {
		Q23 q = new Q23();
		ListNode[] lists = new ListNode[] { new ListNode(1,5), null,new ListNode(4,8,12) };
		for (ListNode l : lists) {
			System.out.println(l);
		}
		System.out.println(q.mergeKLists(lists));
	}

	public ListNode mergeKLists(ListNode[] lists) {
		// 找一个数据结构,向其中插入lists[]中的每一个listnode中的每一个元素,然后将其排序后输出
		ArrayList<Integer> arr = new ArrayList<>();
		for (ListNode l : lists) {
			if (l == null) {
				continue;
			}
			while (l != null) {
				arr.add(l.val);
				l = l.next;
			}
		}
		Collections.sort(arr);
		ListNode l = new ListNode();
		ListNode ans = l;
		for (Integer i : arr) {
			l.next = new ListNode(i);
			l = l.next;
		}

		return ans.next;
	}
//	// 将k个升序排列的链表合并为一个,两个两个链表合并,答案放在靠左的那个链表中,直到全部遍历一遍后,lists[0]处就是结果
//	public ListNode mergeKLists(ListNode[] lists) {
//		if (lists == null) {
//			return null;
//		}
//		if (lists.length == 0) {
//			return null;
//		}
//		if (lists.length == 1) {
//			return lists[0];
//		}
//		for (int i = lists.length - 2; i >= 0; i--) {
//			mergeTwoLists(lists, i);
//		}
//		return lists[0];
//	}
//
//	/**
//	 * 将升序链表数组lists的下标为index处的链表和下标为 index+1 处的链表合并为一个升序排列的链表并存放在Lists[index]处
//	 * 
//	 * @param lists 每个元素都是空的,升序的, 或者为null
//	 * @param index index应该保证本身和右边都是有元素的
//	 */
//	private static void mergeTwoLists(ListNode[] lists, int index) {
//		// 将l1和l2归并在l0处
//		// 虚节点
//		ListNode l1 = lists[index];
//		ListNode l2 = lists[index + 1];
//		ListNode l0 = new ListNode(0);
//		lists[index] = l0;
//		// 归并
//		while (l1 != null && l2 != null) {
//			if (l1.val < l2.val) {
//				l0.next = new ListNode(l1.val);
//				l1 = l1.next;
//			} else {
//				l0.next = new ListNode(l2.val);
//				l2 = l2.next;
//			}
//			l0 = l0.next;
//		}
//		if (l1 == null) {
//			l0.next = l2;
//		} else {
//			l0.next = l1;
//		}
//		lists[index] = lists[index].next;
//
//	}
}
