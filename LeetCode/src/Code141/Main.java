package Code141;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 环形链表
 * @date 2024/4/28
 * @level 简单
 * @url <a href="https://leetcode.cn/problems/linked-list-cycle/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
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
     * 判断链表中是否存在环
     *
     * @param head
     * @return boolean
     */
    public boolean hasCycle(ListNode head) {
        //法一：哈希表
        //return method(head);

        //法二：快慢指针 空间复杂度o(1)
        ListNode fast = head;
        ListNode slow = head;
        //只要快指针没有遍历到尽头，就继续走，只有单独一个节点不会进入循环
        while (fast != null && fast.next != null) {
            //快的走两步
            fast = fast.next.next;
            //慢的走一步
            slow = slow.next;
            //如果快慢指针相遇，则存在环
            if (fast == slow) {
                return true;
            }
        }

        //fast遍历到尽头都没碰上，则说明没有环
        return false;
    }

    private static boolean method(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            //如果存在该节点，则add返回false
            if (!set.add(head)) {
                return true;
            }
            //否则，就添加进去了，遍历下一个
            head = head.next;
        }
        //遍历到了head=null，没有环
        return false;
    }
}