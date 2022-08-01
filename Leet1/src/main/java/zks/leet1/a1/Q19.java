package zks.leet1.a2;

/*
 * 19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

 

示例 1：


输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
示例 2：

输入：head = [1], n = 1
输出：[]
示例 3：

输入：head = [1,2], n = 1
输出：[1]
 

提示：

链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

进阶：你能尝试使用一趟扫描实现吗？
 */
public class Q19 {
	public static void main(String[] args) {
		Q19 q = new Q19();
		ListNode l5 = new ListNode(5);
		ListNode l4 = new ListNode(4, l5);
		ListNode l3 = new ListNode(3, l4);
		ListNode l2 = new ListNode(2, l3);
		ListNode l1 = new ListNode(1, l2);
		System.out.println(l1);
		System.out.println(q.removeNthFromEnd(l1, 2));
//		l3.next = l3.next.next;
//		System.out.println(l1);
	}

	// 思考: 用双指针法,一次扫描即可得到答案, 前指针先走n步,然后后指针和前指针一起走,当前指针到尾部时,后指针做删除操作
	// 细节:前指针向前调用n次next, 这样当前指针走到末尾时,后指针实际指向倒数第n+1个,这时做删除操作可以删除倒数第n个
	// 若指定删除的第n个实际是head, 此时没有调用n次next,先走到了末尾,这种情况下返回head.next
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head.next == null) {
			return null;
		}
		ListNode front = head;
		ListNode back = head;
		ListNode ansr = head;
		for (int i = 0; i < n; i++) {// 前指针前进n次
			front = front.next;
			if (front == null) {// 这里是删除第一个元素
				return head.next;
			}
		}
		while (front.next != null) {
			front = front.next;
			back = back.next;
		}
		back.next = back.next.next;
		return ansr;
	}
//从头到尾调用next,获得链表的规模,然后再从头到尾去遍历,删除掉倒数第n个	这个做法的效率是够的
//	public ListNode removeNthFromEnd(ListNode head, int n) {
//		int size = 1;
//		ListNode l0 = head;
//		while (true) {
//			if (head.next == null) {
//				break;
//			}
//			head = head.next;
//			size++;
//		}
//		ListNode l = l0;
//		// 删除操作,若是删正数第一个,应该返回第二个元素
//		if (n == size) {
//			return l0.next;
//		} else {// 其余情况下,都是删除指定元素后,返回head
//			for (int i = 0; i < size - n - 1; i++) {// 删除应该去应该删除的元素前面操作
//				l = l.next;
//			}
//			l.next = l.next.next;
//		}
//		return l0;
//	}

}

//  Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListNode l = this;
		sb.append('[');
		while (l.next != null) {
			sb.append(l.val);
			sb.append(", ");
			l = l.next;
		}
		sb.append(l.val);
		sb.append(']');
		return new String(sb);
	}

}
