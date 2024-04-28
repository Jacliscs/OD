package Code234;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 回文链表
 * @date 2024/4/28
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/palindrome-linked-list/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //创建[1,2,2,1]链表
        Solution.ListNode head = new Solution.ListNode(1);
        head.add(2);
        head.add(3);
        head.add(4);
        //打印链表head的值
        Solution.ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }


        System.out.println(new Solution().isPalindrome(head));

    }
}

class Solution {
    public static class ListNode {
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

        //在链表末尾添加元素
        public void add(int val) {
            ListNode tmp = new ListNode(val, null);
            ListNode cur = this;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = tmp;
        }

    }

    public boolean isPalindrome(ListNode head) {
        //不能反转整个链表，只能反转一半，前半部分不能动
        if (head == null || head.next == null) return true;

        //用快慢指针找到前半部分的尾节点
        ListNode half_tail = findHalf_tail(head);

        //反转尾结点之后的链表
        ListNode reverseStart = reverseListNode(half_tail.next);

        //如果是回文，head与reverseStart应该在有限值内相同
        while (reverseStart != null) {
            if (head.val != reverseStart.val) return false;

            head = head.next;
            reverseStart = reverseStart.next;
        }

        return true;

    }

    /**
     * 返回链表前半部分的尾结点
     *
     * @param head
     * @return Code234.Solution.ListNode
     */
    public static ListNode findHalf_tail(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        //快指针移动到尾结点时，慢指针刚好到一半
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return Code234.Solution.ListNode
     */
    private static ListNode reverseListNode(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            //cur指向ore
            cur.next = pre;

            //更新状态
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}