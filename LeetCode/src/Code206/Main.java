package Code206;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author 浮生
 * @description 反转链表
 * @date 2024/4/7
 * @level 简单
 * @score
 * @url https://leetcode.cn/problems/reverse-linked-list/description/
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    //输入：head = [1,2,3,4,5]
    //输出：[5,4,3,2,1]
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution s = new Solution();
        //创建链表 0->1->2->3
        Solution.ListNode head = new Solution.ListNode(0);
        head.addLast(1);
        head.addLast(2);
        head.addLast(3);

        //打印原链表
        s.printList(head);

        //反转链表
        Solution.ListNode newHead = new Solution().reverseList(head);

        //打印反转后的链表
        s.printList(newHead);

    }
}


class Solution {
    /**
     * 链表定义
     */
    public static class ListNode {
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

        //在末尾新增元素
        public void addLast(int val) {
            //如果是末尾元素
            if (this.next == null) {
                this.next = new ListNode(val);
            } else {
                //不是末尾元素
                ListNode cur = this;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = new ListNode(val);
            }
        }

    }

    //打印链表
    public void printList(ListNode head) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        while (head != null) {
            sj.add(head.val + "");
            head = head.next;
        }
        System.out.println(sj);
    }

    public ListNode reverseList(ListNode head) {
        // 0->1->2->3->null    3->2->1->0->null
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            //把cur.next指向pre，然后往后遍历
            ListNode temp = cur.next;
            cur.next = pre;
            //更新上一节点
            pre = cur;
            cur = temp;
        }
        //最后一个节点最后赋值给pre，cur=null
        return pre;
    }
}