package Code148;

import java.util.*;

/**
 * @author 浮生
 * @description 排序链表
 * @date 2024/4/28
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/sort-list/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 排序链表-转数组方法
     *
     * @param head
     * @return Code148.Solution.ListNode
     */
    public ListNode sortList(ListNode head) {
        //转list，排序后转链表
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        //升序排序
        Collections.sort(list);
        //哑结点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        for (int n : list) {
            ListNode node = new ListNode(n);
            cur.next = node;
            //遍历下一个
            cur = node;

        }

        return dummy.next;

    }
}