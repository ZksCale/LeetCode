package zks.leet1.a3;

/*
 * 21. 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

 

示例 1：


输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]
示例 2：

输入：l1 = [], l2 = []
输出：[]
示例 3：

输入：l1 = [], l2 = [0]
输出：[0]
 

提示：

两个链表的节点数目范围是 [0, 50]
-100 <= Node.val <= 100
l1 和 l2 均按 非递减顺序 排列
 */
public class Q21 {
	public static void main(String[] args) {
		Q21 q = new Q21();
		ListNode l1 = null;
		ListNode l2 = null;
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(q.mergeTwoLists(l1, l2));
	}

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode ansr = new ListNode(0);//开头是个虚节点
		ListNode l0 = ansr;
		ListNode l1 = list1;
		ListNode l2 = list2;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				l0.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				l0.next = new ListNode(l2.val);
				l2 = l2.next;
			}
			l0 = l0.next;
		}
		if (l1 == null) {
			l0.next = l2;
		} else if (l2 == null) {
			l0.next = l1;
		}
		return ansr.next;//去掉虚节点
	}

}
