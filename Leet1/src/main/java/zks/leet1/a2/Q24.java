package zks.leet1.a3;

/*
 * 24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

 

示例 1：


输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：

输入：head = []
输出：[]
示例 3：

输入：head = [1]
输出：[1]
 

提示：

链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
 */
public class Q24 {
	public static void main(String[] args) {
		Q24 q = new Q24();
		ListNode head = new ListNode(1, 2,3,4);
		System.out.println(head);

		System.out.println(q.swapPairs(head));
	}

//通过改变next的指向而非更改链表的值做到
	public ListNode swapPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		ListNode l = new ListNode(0, head);// 虚节点
		ListNode ans = l;
		while (true) {
			ListNode l0 = l.next;
			ListNode l1 = l0.next;
			// l->l0->l1->
			// l->l1-l0->

			l.next = l1;
			l0.next = l1.next;
			l1.next = l0;
			l = l.next.next;
			if (l.next == null || l.next.next == null) {
				break;
			}
		}
		return ans.next;
	}
}
