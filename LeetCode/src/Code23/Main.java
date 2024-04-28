package Code23;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 合并k个升序链表
 * @date 2024/4/28
 * @level 困难
 * @url <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表
     *
     * @param lists
     * @return Code23.Solution.ListNode
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //找到每次链表里最小的ListNode min，然后递归min.next = mergeKLists()
        //使用优先队列 小根堆
        Queue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        //把所有链表头结点添加，自动排序
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        //哑结点
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!queue.isEmpty()) {
            //每次取出最小的
            ListNode minNode = queue.poll();

            //更新当前已排序的尾结点
            tail.next = minNode;
            tail = tail.next;

            //如果该节点后面还有，则将其作为头结点加入队列
            if (minNode.next != null) {
                queue.offer(minNode.next);
            }
        }

        //返回排序后的头结点
        return dummy.next;
    }
}