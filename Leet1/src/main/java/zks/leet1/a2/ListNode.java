package zks.leet1.a2;

//Definition for singly-linked list.
public class ListNode {
	public static void main(String[] args) {
		ListNode l = new ListNode(1, 2, 3);
		l = new ListNode(0, l);
		System.out.println(l);
		l = l.next;
		System.out.println(l);
	}

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

	ListNode(int... arr) {
		ListNode l = this;
		this.val = arr[0];
		for (int i = 1; i < arr.length; i++) {
			l.next = new ListNode(arr[i]);
			l = l.next;
		}
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
