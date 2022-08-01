package zks.leet1.a3;

/*
 * 25. K 个一组翻转链表
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 

示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
示例 2：


输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
示例 3：

输入：head = [1,2,3,4,5], k = 1
输出：[1,2,3,4,5]
示例 4：

输入：head = [1], k = 1
输出：[1]
提示：

列表中节点的数量在范围 sz 内
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz
 */
public class Q25 {
	public static void main(String[] args) {
		Q25 q = new Q25();
		ListNode head = new ListNode(1, 2,3,4,5,6,7,8,9,10);
		int k = 3;
		System.out.println(head);
		System.out.println(q.reverseKGroup(head, k));
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (k == 1) {
			return head;
		}
		ListNode l = new ListNode(0, head);
		ListNode ans = l;
		// 调用翻转函数一段一段地翻转链表
		Label: while (true) {
			reverseList(l, k);
			for (int i = 0; i < k; i++) {// 前进k次后调用一次
				l = l.next;
				if (l == null) {
					break Label;
				}
			}
		}
		return ans.next;
	}

	/**
	 * 翻转head之后的k个元素, 注意head是不被翻转的
	 */
	private static void reverseList(ListNode head, int k) {
		// pHead, pTail, pNext, p 四个指针
		ListNode pHead = head;
		ListNode pTail = head;
		for (int i = 0; i < k; i++) {// 去找pTail
			pTail = pTail.next;
			if (pTail == null) {
				// 这说明等待翻转的链表不够长,不用翻转了
				return;
			}
		}
		pTail = pTail.next;
		ListNode p = pHead.next;// 指向被移到末尾的元素
		ListNode pNext = p.next;// 指向下一个要操作的元素
		for (int i = 0; i < k - 1; i++) {
			p.next = pTail;
			pTail = p;
			p = pNext;
			pNext = pNext.next;
		}
		p.next = pTail;
		pHead.next = p;
	}
}
