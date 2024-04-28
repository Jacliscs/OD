package Code146.LRU;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author 浮生
 * @description LRU缓存-双向链表做法
 * @date 2024/4/28
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/lru-cache/description/?envType=study-plan-v2&envId=top-100-liked">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class LRUCache {
    //双向链表 方便快速定位、删除、移动到开头
    public static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //缓存大小
    int capacity;
    //哨兵节点，方便移动操作
    Node dummy = new Node(-1, -1);

    //存放缓存，最前面的是最近访问的，最后面的是内存不够时要丢弃的
    HashMap<Integer, Node> map = new HashMap<>();

    //初始化内存大小
    public LRUCache(int capacity) {
        this.capacity = capacity;
        //哨兵节点，双向链表首尾相连
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        //获取节点，并将其放到最新的位置
        Node node = getNode(key);

        //如果没有对应key相同的node，则返回-1
        return node == null ? -1 : node.value;
    }

    public void put(int key, int value) {
        //获取，如果有的话会自动更新到顶层，没有的话node=null
        Node node = getNode(key);
        //有则更新value
        if (node != null) {
            node.value = value;
            return;
        }

        //没有的话，则新建
        node = new Node(key, value);
        //放进map
        map.put(key, node);
        //node插入到双向链表头部
        refresh(node);

        //如果超过容量，则删除尾结点
        if (map.size() > capacity) {
            //因为是头尾相连的，dummy.prev就是尾结点
            Node delete = dummy.prev;

            //从map中移除(delete.key,delete)
            map.remove(delete.key);

            //双向链表中删除delete
            remove(delete);
        }
    }

    /**
     * 在双向链表里面取node，如果没有则返回null，有则返回并更新到链表头部
     *
     * @param key
     * @return Code146.LRU.LRUCache.Node
     */
    private Node getNode(int key) {
        //如果不存在key，则返回null
        if (!map.containsKey(key)) return null;

        //存在，则取出，再放到最上层
        Node node = map.get(key);

        //取出
        remove(node);

        //放到顶层 dummy -> node -> ....
        refresh(node);

        //返回取到的节点，此时已经更新了node
        return node;
    }

    /**
     * 在双向链表里面删除x
     *
     * @param x
     * @return void
     */
    private void remove(Node x) {
        //pre -> x -> nex
        x.next.prev = x.prev;
        x.prev.next = x.next;
    }

    /**
     * 把节点x放到链表开头
     *
     * @param x
     * @return void
     */
    private void refresh(Node x) {
        //x插入到dummy与dummy.next之间
        x.prev = dummy;
        x.next = dummy.next;

        //让x前后链接上x
        x.prev.next = x;
        x.next.prev = x;
    }
}