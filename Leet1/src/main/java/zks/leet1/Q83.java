package zks.leet1;

/*
83. 删除排序链表中的重复元素
给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。



示例 1：


输入：head = [1,1,2]
输出：[1,2]
示例 2：


输入：head = [1,1,2,3,3]
输出：[1,2,3]


提示：

链表中节点数目在范围 [0, 300] 内
-100 <= Node.val <= 100
题目数据保证链表已经按升序 排列
 */
public class Q83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ans = new ListNode(0, head);
        ListNode p = ans;
        while(p.next!=null){
            if(p.next.next!=null&&p.next.val==p.next.next.val){
                ListNode f=p.next.next;
                while(f.next!=null&&f.val==f.next.val) f=f.next;
                p.next=f;
            }else{
                p=p.next;
            }
        }
        return ans.next;
    }
}
