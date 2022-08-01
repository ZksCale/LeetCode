package zks.leet1.a0;
/*
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 

示例 1：


输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
示例 2：

输入：l1 = [0], l2 = [0]
输出：[0]
示例 3：

输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
9999999 + 9999 = 10009998
输出：[8,9,9,9,0,0,0,1]
 

提示：

每个链表中的节点数在范围 [1, 100] 内
0 <= Node.val <= 9
题目数据保证列表表示的数字不含前导零

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

//本题的问题是数字非常大, 转换成基本数据类型是会上溢的

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
			sb.append(',');
		}
		sb.append(l.val);
		sb.append(']');
		return new String(sb);
	}

}

public class Q2 {
	public static void main(String[] args) {
		
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l0 = l1;
		while(true) {
			l1.val += l2.val;
			fullCarry(l1);
			if(l1.next == null) {
				if(l2.next != null) {
					l1.next = l2.next;
					break;
				}else {
					break;
				}
			}else {
				l1 = l1.next;
			}
			if(l2.next == null) {
				break;
			}else {
				l2 = l2.next;
			}
			
			
		}
		return l0;
	}
	public static void fullCarry(ListNode l) {
		if(l.next == null) {
			if(l.val >= 10) {
				l.val -= 10;
				l.next = new ListNode(1);
			}
		}else {
			if(l.val >= 10) {
				l.val -= 10;
				l.next.val++;
				fullCarry(l.next);
			}
		}
		
	}
}
