package Code24;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 两两交换链表中的节点
 * @date 2024/4/28
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 两两交换其中相邻的节点，并返回交换后链表的头节点
     *
     * @param head
     * @return Code24.Solution.ListNode
     */
    public ListNode swapPairs(ListNode head) {
        //空间o(1) 每次遍历三个节点 tmp -> node1 -> node2 更改后 tmp -> node2 -> node1 ->node2.next
        //添加一个哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode tmp = dummy;
        //只要后面还有俩节点，就继续循环
        while (tmp.next != null && tmp.next.next != null) {
            ListNode node1 = tmp.next;
            ListNode node2 = node1.next;

            //交换node1和node2
            tmp.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            //更新tmp，下次交换node1后面的俩节点
            tmp = node1;
        }

        //返回交换后的头结点
        return dummy.next;

    }
}