package Code25;

import java.util.Scanner;

/**
 * @author 浮生
 * @description k个一组翻转列表
 * @date 2024/4/28
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 每 k 个节点一组进行翻转，请你返回修改后的链表,最后不足k个的保持不变
     *
     * @param head
     * @param k
     * @return Code25.Solution.ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        //哨兵节点
        ListNode dummy = new ListNode(0, head);

        //初始化  pre [start,....,end] next
        //pre是本次翻转链表的前驱 next是本次翻转链表尾结点的后继
        //start是本次翻转链表的头结点，end是尾结点
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            //end往后移动k个 不足k个了则直接跳出
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            //如果end走到末尾了，说明最后一组不足k个了
            if (end == null) break;

            //反转链表的头结点
            ListNode start = pre.next;
            //下一个反转的头结点
            ListNode nextStart = end.next;

            //只反转[start,..,end]的节点，把end后面的断开
            end.next = null;

            //前驱节点连接翻转后的头结点
            pre.next = reverse(start);

            //反转后，变成pre [end,....,start] nextStart
            start.next = nextStart;

            //更新下一组需要翻转的前驱
            pre = start;
            end = pre;

        }

        //返回翻转后的头结点
        return dummy.next;
    }

    public static ListNode reverse(ListNode head) {
        //哨兵节点
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            //更新，下一个节点的前驱
            pre = cur;
            cur = next;
        }

        //返回头结点，最后一步 pre = head的尾结点
        return pre;
    }
}