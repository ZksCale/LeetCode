package zks.leet1.a9;

import org.junit.jupiter.api.Test;

/*
92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。


示例 1：


输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
示例 2：

输入：head = [5], left = 1, right = 1
输出：[5]


提示：

链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n


进阶： 你可以使用一趟扫描完成反转吗？
 */
public class Q92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode l = new ListNode(0,head), r = l,l0=l;
        for (int i = 0; i < left - 1; i++) l = l.next;
        for (int i = 0; i <= right; i++) r = r.next;
        //应该操作的节点个数为:right-left+1
        //假定链表示从左向右指 那么l指向翻转区域左边的第一个节点, r指向翻转区域右边的第一个节点
        ListNode n = l.next;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = n.next;
            n.next = r;
            r = n;
            n = next;
        }
        l.next=r;
        return l0.next;
    }
    @Test
    public void t(){
        ListNode head=new ListNode(1,2,3);
        head=reverseBetween(head,1,3);
        System.out.println(head);
    }
}
