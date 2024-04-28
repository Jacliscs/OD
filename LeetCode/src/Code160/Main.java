package Code160;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 相交链表
 * @date 2024/4/28
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/?envType=study-plan-v2&envId=top-100-liked">url</a>
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


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //都往后面遍历，如果为空就从另一个的头部遍历，最多走一长一短的距离就可以同时到达交点或者null
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;

    }
}