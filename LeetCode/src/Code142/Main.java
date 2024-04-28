package Code142;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 环形链表II
 * @date 2024/4/28
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     *
     * @param head
     * @return Code142.Solution.ListNode
     */
    public ListNode detectCycle(ListNode head) {
        //法一：哈希表存放遍历过的节点 空间o(n)
        //return method(head);

        //法二：快慢指针 空间o(1)
        ListNode fast = head;
        ListNode slow = head;

        //当fast不为空且下一个值也不会空时，这样单节点就不会进入循环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //如果快慢指针相遇，则说明有环
            if (fast == slow) break;
        }

        //判断是因为fast走到尽头还是因为相遇退出循环
        if (fast == null || fast.next == null) return null;

        //快指针从头再走一次，fast和slow会相遇在环的第一个节点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    private static ListNode method(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            //尝试添加，如果该节点已经添加过，则是入环的第一个节点，返回
            if (!set.add(head)) return head;

            //遍历下一个
            head = head.next;
        }

        //遍历完没有环，此时head=null
        return head;
    }
}