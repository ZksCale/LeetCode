package zks.leet1;

//  Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int... ints) {
        this.val = ints[0];
        ListNode[] nodes = new ListNode[ints.length];
        nodes[0] = this;
        for (int i = 1; i < ints.length; i++) {
            nodes[i] = new ListNode(ints[i]);
            nodes[i - 1].next = nodes[i];
        }
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode l = this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        while (l.next != null) {
            stringBuilder.append(l.val);
            stringBuilder.append(", ");
            l = l.next;
        }
        stringBuilder.append(l.val);
        stringBuilder.append(']');
        return new String(stringBuilder);
    }
}

