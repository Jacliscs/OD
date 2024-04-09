package Code2;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 两数相加
 * @date 2024/4/9
 * @level 中等
 * @score
 * @url https://leetcode.cn/problems/add-two-numbers/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //头结点，尾结点
        ListNode head = null, tail = null;
        //进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;

            //初始化，头节点和尾结点是同一个节点
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                //否则，就加在尾结点后面
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            //遍历l1下一个节点
            if (l1 != null) {
                l1 = l1.next;
            }

            //遍历l2下一个节点
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //如果最后还有进位
        if (carry > 0) {
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}