package OD359.pq;

import javax.lang.model.type.IntersectionType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 浮生
 * @description 执行任务赚积分-优先队列做法
 * @date 2024/4/14
 * @level
 * @score
 * @url
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //有n个任务
        int n = sc.nextInt();

        //有多少时间能完成任务==可以做多少个任务
        int t = sc.nextInt();

        //任务：最晚处理时间，积分值
        int[][] tasks = new int[n][2];
        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextInt();
            tasks[i][1] = sc.nextInt();
        }

        //输出最多可获得的积分值
        System.out.println(getResult(tasks, t));


    }

    public static int getResult(int[][] tasks, int t) {
        //把任务按最晚处理时间升序
        Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));

        //存放可能获得的积分值，积分越小优先级越高，方便替换，最后仅保留t个
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));

        //当前时间 从0开始
        int curTime = 0;

        //开始遍历任务
        for (int i = 0; i < tasks.length; i++) {
            //最晚处理时间
            int endTime = tasks[i][0];
            //积分值
            int score = tasks[i][1];

            //如果当前时间小于最晚处理时间，则可以直接加入队列，自动排序
            if (curTime < endTime) {
                pq.offer(score);
                //当前时间+1
                curTime++;
            } else {
                //curTime >= endTime 与pq的队首元素比较，保留较大值
                if (score > pq.peek()) {
                    //每次只需要移出一个
                    pq.poll();
                    //加入新的
                    pq.offer(score);
                    //不改变当前时间
                }
            }
        }

        //现在pq里保存的是可能的积分值，只取最大的t个
        while (pq.size() > t) {
            pq.poll();
        }
        //最后pq中的积分是可获得的，求和返回
        return pq.stream().mapToInt(a -> a).sum();
    }
}