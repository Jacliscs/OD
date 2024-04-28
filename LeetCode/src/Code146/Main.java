package Code146;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description LRU缓存
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
    //最大缓存
    int maxCapacity;
    //已使用缓存
    int usedCapacity;

    //维护最近访问时间
    static int time;
    //优先队列存放缓存：访问时间最小的在最前面
    Queue<Cache> caches = new PriorityQueue<>((a, b) -> a.recentTime - b.recentTime);


    //初始化内存大小
    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.usedCapacity = 0;
        //初始化访问时间
        time = 0;
    }

    public int get(int key) {
        //如果存在，则返回value，并更新最新的访问时间
        for (Cache cache : caches) {
            if (cache.key == key) {
                //先移出再添加，方便排序
                caches.remove(cache);

                cache.recentTime = time++;
                caches.add(cache);

                //找到了就返回key对应的值
                return cache.value;
            }
        }

        //如果不存在
        return -1;
    }

    public void put(int key, int value) {
        //先判断是否存在key，存在则修改value，更新time
        for (Cache cache : caches) {
            if (cache.key == key) {
                //先移出再添加，方便排序
                caches.remove(cache);

                cache.value = value;
                cache.recentTime = time++;
                caches.add(cache);

                //找到了就返回key对应的值
                return;
            }
        }

        //如果不存在，则添加
        if (usedCapacity < maxCapacity) {
            //添加
            caches.add(new Cache(key, value));
            usedCapacity++;
        } else {
            //移除最近访问时间最小的
            caches.poll();
            //添加
            caches.add(new Cache(key, value));
        }

    }

    //缓存类
    static class Cache {
        //key
        int key;
        int value;

        //访问时间
        int recentTime;

        public Cache(int key, int value) {
            this.key = key;
            this.value = value;
            //修改最近访问时间
            this.recentTime = time++;
        }
    }

}