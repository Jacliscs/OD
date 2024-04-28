package Code138;


import java.util.HashMap;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 随机链表的复制
 * @date 2024/4/28
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class Solution {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        //判空
        if (head == null) return null;

        //先遍历一遍，简单复制对应的值和新节点，还没有链接新链表
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        //第二次遍历，将对应的关系复制
        cur = head;
        while (cur != null) {
            //对应next
            map.get(cur).next = map.get(cur.next);
            //对应random
            map.get(cur).random = map.get(cur.random);

            //下一位
            cur = cur.next;
        }

        //返回复制的头结点
        return map.get(head);

    }
}