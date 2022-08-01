package zks.leet1;

import org.junit.jupiter.api.Test;

/*
86. 分隔链表
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

你应当 保留 两个分区中每个节点的初始相对位置。



示例 1：


输入：head = [1,4,3,2,5,2], x = 3
输出：[1,2,2,4,3,5]
示例 2：

输入：head = [2,1], x = 2
输出：[1,2]


提示：

链表中节点的数目在范围 [0, 200] 内
-100 <= Node.val <= 100
-200 <= x <= 200
 */
public class Q86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode l = new ListNode();
        ListNode r = new ListNode();
        ListNode l0=l,r0=r;
        while (head!=null){
            if(head.val<x){
                l.next=head;
                head= head.next;
                l=l.next;
                l.next=null;
            }else{
                r.next=head;
                head= head.next;
                r=r.next;
                r.next=null;
            }

        }
        if(r0.next!=null) l.next=r0.next;
        return l0.next;
    }

    @Test
    public void t86(){
        ListNode node = new ListNode(1, 4, 3, 2, 5, 2);
        ListNode l = partition(node, 3);
        System.out.println(node);
    }
}
