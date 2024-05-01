package Code1845;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 座位预约管理系统
 * @date 2024/5/1
 * @level 中等
 * @url <a href="https://leetcode.cn/problems/seat-reservation-manager/description/">url</a>
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}

class SeatManager {
    //存放可预约的座位编号，越小的越在前
    PriorityQueue<Integer> queue;

    //初始化
    public SeatManager(int n) {
        queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        //把1-n添加进可预约队列
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
    }

    //返回可以预约座位的做小编号
    public int reserve() {
        //可预约队列的第一个 由题目保证调用时至少有一个可预约的
        return queue.poll();
    }

    //解预约
    public void unreserve(int seatNumber) {
        //题目保证调用时该座位处于被预约状态，即不在队列中
        queue.offer(seatNumber);
    }
}
