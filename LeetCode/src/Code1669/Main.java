package Code1669;

import java.util.Scanner;

/**
 * @author 浮生
 * @description 合并两个链表
 * @date 2024/4/19
 * @level 中等
 * @score
 * @url
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
     * 把list2接在list1的a，b位置
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return Code1669.Solution.ListNode
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        //list2的尾结点
        ListNode list2_tail = list2;
        while (list2_tail.next != null) list2_tail = list2_tail.next;

        //让list1_pre指向list1的a-1节点
        ListNode list1_pre = list1;
        for (int i = 0; i < a - 1; i++) {
            list1_pre = list1_pre.next;
        }

        //指向list1的b+1节点
        ListNode list1_next = list1;
        for (int i = 0; i < b + 1; i++) {
            list1_next = list1_next.next;
        }

        //list1的a-1节点指向list2头结点，list2尾结点指向list1的b+1节点
        list1_pre.next = list2;
        list2_tail.next = list1_next;

        return list1;
    }
}