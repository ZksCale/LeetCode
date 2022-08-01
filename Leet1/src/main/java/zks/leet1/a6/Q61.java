package zks.leet1.a7;

/*
61. 旋转链表
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。



示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
示例 2：


输入：head = [0,1,2], k = 4
输出：[2,0,1]


提示：

链表中节点的数目在范围 [0, 500] 内
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */
public class Q61 {
    /*
    考虑将这个链表首位相连,形成一个环,答案无非是将这个环切割开,以某个元素作为新的头部返回
    所以要做的是,遍历这个链表,直到它的尾巴,这样就可以得到链表的长度,记做len
    然后将链表首位相连
    接着,根据len和k的值,将它重新切割
    最后返回答案
    时间复杂度:O(n+n)=O(n)  一次遍历和一次切割
    空间复杂度:O(1) 原地操作
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;
        //遍历,找到最末尾的tail,顺带计算长度len
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }//将它首尾相连
        tail.next = head;
        k %= len;
        //然后在合适的位置切割它(旋转k次相当于指针逆时针走k步,也就等效于顺时针走len-k步)
        //所以tail前进len-k步
        for (int i = 0; i < len - k; i++) tail = tail.next;
        //这时tail确实处在答案序列中的尾部,在这里切割,并返回tail.next
        ListNode ans = tail.next;
        tail.next = null;
        return ans;
    }
}

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
}

