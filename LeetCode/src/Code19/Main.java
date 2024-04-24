package Code19;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 删除链表的倒数第n个节点
 * @date 2024/4/24
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    public class ListNode {
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


    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * @param head
     * @param n
     * @return Code19.Solution.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //在头结点前面添加一个哑结点 可以减少删除的节点是头结点时的判断
        ListNode dummy = new ListNode(0, head);
        //获取链表长度
        int length = getLength(head);

        //删除的倒数第n个节点x，是从head开始正数的length-n+1个，从dummy数就是x的前驱节点，
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; i++) {
            cur = cur.next;
        }
        //cur是要删除节点x的前驱节点 指向x的后继结点
        //如果要删除的头节点，cur就是dummy，返回的dummy.next就是头结点
        cur.next = cur.next.next;

        //删除后的头结点
        return dummy.next;
    }

    /**
     * 返回链表长度
     *
     * @param head
     * @return int
     */
    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}