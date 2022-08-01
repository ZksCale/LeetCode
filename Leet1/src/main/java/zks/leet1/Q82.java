package zks.leet1;

import java.util.List;

/*
82. 删除排序链表中的重复元素 II
给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。



示例 1：


输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
示例 2：


输入：head = [1,1,1,2,3]
输出：[2,3]


提示：

链表中节点数目在范围 [0, 300] 内
-100 <= Node.val <= 100
题目数据保证链表已经按升序 排列
 */
public class Q82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ans = new ListNode(-1000, head);
        ListNode p = ans;
        while (p.next != null) {
            if (p.next.next != null && p.next.val == p.next.next.val) {
                ListNode f=p.next.next;
                while(f.next!=null&&f.val==f.next.val) f=f.next;
                p.next=f.next;
            }else{
                p=p.next;
            }
        }

        return ans.next;
    }
}
